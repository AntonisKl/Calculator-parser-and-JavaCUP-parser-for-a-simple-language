from GeneratorClass import Generator

declarationsNum = 1000
callsNum = 1500

for i in range(1, 10):
    declarationsNum = i*100
    callsNum = i*100 + 200
    generator = Generator(declarationsNum, callsNum, "generated_program" + str(declarationsNum) + "_" + str(callsNum) + ".txt")
    generator.createProgram()
