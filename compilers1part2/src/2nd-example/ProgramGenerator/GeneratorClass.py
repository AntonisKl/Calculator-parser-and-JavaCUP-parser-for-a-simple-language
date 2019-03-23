import random


class Generator:

    def __init__(self, declarationsNum, expressionsNum, outputFileName):
        self.declarationsNum = declarationsNum
        self.expressionsNum = expressionsNum
        self.stringLiterals = ["\"John\"", "\"Doe\"", "\"Maria\"", "\"Yolo\""]
        self.builtInFunctions = ["suffix", "prefix"]
        self.ids = ["var1", "var2", "var3", "var4", "var5"]
        self.functionNames = []
        self.outputFileName = outputFileName
        for i in range(declarationsNum):
            self.functionNames.append("function" + str(i))

    def createConcatenation(self, ids):
        returnValue = ""
        for i in range(random.randint(1, 3)):
            if i != 0:
                returnValue += " + "
            returnValue += self.createIdOrLiteral(ids)

        return returnValue

    def createIdOrLiteral(self, ids):
        prob = random.random()
        if prob < 0.5:
            return random.choice(self.stringLiterals)
        else:
            if ids is not None:
                return random.choice(ids)
            else:
                return random.choice(self.stringLiterals)

    def createBuiltInFunction(self, ids):
        prob = random.random()
        if prob < 0.5:
            return random.choice(self.stringLiterals) + " " + random.choice(
                self.builtInFunctions) + " " + random.choice(self.stringLiterals)
        else:
            if ids is not None:
                return random.choice(ids) + " " + random.choice(self.builtInFunctions) + " " + random.choice(
                    ids)
            else:
                return random.choice(self.stringLiterals) + " " + random.choice(
                    self.builtInFunctions) + " " + random.choice(self.stringLiterals)

    def createIfElse(self, ids):
        returnValue = "if (" + self.createBuiltInFunction(ids) + ")\n"
        returnValue += self.createExpression(ids)
        returnValue += "else\n" + self.createExpression(ids)

        return returnValue

    def createExpression(self, ids):
        prob = random.random()
        if prob < 0.3:
            return "\t" + self.createIdOrLiteral(ids) + "\n"
        if prob < 0.6:
            return "\t" + self.createConcatenation(ids) + "\n"
        # if prob < 0.75:
        return "\t" + self.createIfElse(ids)

        # return self.createExpression(ids)

    def createFunctionCall(self):
        returnValue = random.choice(self.functionNames) + "(\n"
        for i in range(5):
            if i != 0:
                returnValue += ", "
            returnValue += self.createExpression(None)

        returnValue += ")\n"
        return returnValue

    def generateIds(self):
        ids = []
        for i in range(5):
            ids.append(self.ids[i])

        return ids

    def createDeclaration(self, functionName):
        returnValue = functionName + "("
        ids = self.generateIds()
        for i in range(len(ids)):
            if i != 0:
                returnValue += ", "
            returnValue += ids[i]

        returnValue += ") {\n"
        # for i in range(random.randint(0, 5)):
        returnValue += "\t" + self.createExpression(ids)

        returnValue += "}\n\n"
        return returnValue

    def createDeclarations(self):
        returnValue = ""
        for functionName in self.functionNames:
            returnValue += self.createDeclaration(functionName)

        return returnValue

    def createExpressions(self):
        returnValue = ""

        for i in range(self.expressionsNum):
            returnValue += self.createFunctionCall()

        return returnValue

    def createProgram(self):
        programS = ""
        programS += self.createDeclarations()
        programS += self.createExpressions()

        print(programS)

        file = open(self.outputFileName, "w")

        file.write(programS)
