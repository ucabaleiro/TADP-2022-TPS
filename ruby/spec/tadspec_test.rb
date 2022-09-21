require_relative '../lib/tadspec'
class Docente
  def initialize(edad)
    @edad = edad
  end

  def edad
    @edad
  end

  def viejo?
    @edad > 29
  end
end

class DocenteTest
  def testear_que_lean_tiene_22
    leandro = Docente.new(22)
    puts "estoy dentro de un test!! #{leandro.edad.deberia ser 22}"
  end
end

nico = Docente.new(30)
leandro = Docente.new(22)
docentes = [nico, leandro]

# puts 1.deberia ser 1.0
# puts 1.deberia ser_igual 1.0
#
# puts 1.deberia ser menor_a 2
# puts 1.deberia ser menor_a 0
#
# puts 1.deberia menor_a 2
#
# puts nico.viejo?.deberia ser true
# puts "nico.deberia ser_viejo: #{nico.deberia ser_viejo}"
#
# puts leandro.edad.deberia ser uno_de_estos ["pepe", 22, "lala"]
# puts leandro.edad.deberia ser uno_de_estos ["pepe", 2332, "lala"]
# puts leandro.edad.deberia ser uno_de_estos 7, 22, "hola"
# puts leandro.edad.deberia ser uno_de_estos 7, 22324, "hola"
#
# puts "leandro deberia entender :viejo?: #{leandro.deberia entender :viejo?}"
# puts "leandro no deberia entender :capo?: #{leandro.deberia entender :capo?}"
#
# puts "leandro deberia tener_edad 22 -- #{leandro.deberia tener_edad 22}"
# puts "leandro deberia tener_edad 2 -- #{leandro.deberia tener_edad 23}"
#
# puts leandro.deberia tener_nombre "leandro" # falla: no hay atributo nombre
# puts leandro.deberia tener_edad 22 # pasa
# puts leandro.deberia tener_nombre n=il # pasa
# puts leandro.deberia(self.tener_edad(self.mayor_a(20))) # pasa
# puts leandro.deberia tener_edad menor_a 21 # pasa
# puts leandro.deberia tener_edad uno_de_estos [7, 22, "hola"] # pasa

# TADsPec.testear
