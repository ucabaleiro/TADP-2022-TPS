require 'rspec'
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
