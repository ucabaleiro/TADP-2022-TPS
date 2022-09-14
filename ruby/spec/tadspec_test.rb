require_relative '../lib/tadspec'

class Docente
  attr_accessor :edad

  def initialize(edad)
    self.edad = edad
  end

  def viejo?
    self.edad > 29
  end
end

nico = Docente.new(30)
leandro = Docente.new(22)
docentes = [nico, leandro]

puts 1.deberia ser 1.0
puts 1.deberia ser_igual 1.0

puts 1.deberia ser menor_a 2
puts 1.deberia ser menor_a 0

puts 1.deberia menor_a 2

puts nico.viejo?.deberia ser true
puts "leandro.deberia ser_viejo: #{leandro.deberia ser_viejo}"
puts "nico.deberia ser_viejo: #{nico.deberia ser_viejo}"

puts leandro.edad.deberia ser uno_de_estos ["pepe", 22, "lala"]
puts leandro.edad.deberia ser uno_de_estos ["pepe", 2332, "lala"]
puts leandro.edad.deberia ser uno_de_estos 7, 22, "hola"
puts leandro.edad.deberia ser uno_de_estos 7, 22324, "hola"
