from GeneratorClass import Generator

for i in range(1, 11):
    generator = Generator(i, i, "generated_program" + str(i) + ".txt")
    generator.createProgram()
