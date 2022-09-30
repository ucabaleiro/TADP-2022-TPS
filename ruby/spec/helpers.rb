require 'rspec'
require 'singleton'
require_relative '../lib/tadspec'

class Persona
  def initialize(edad)
    @edad = edad
  end

  def edad
    @edad
  end

  def viejo?
    self.edad > 29
  end
end
