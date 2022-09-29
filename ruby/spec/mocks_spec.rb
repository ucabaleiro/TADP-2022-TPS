require_relative './helpers'

class Persona
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

class PersonaHome
  def self.todas_las_personas
    # Este método consume un servicio web que consulta una base de datos
  end

  def self.personas_viejas
    self.todas_las_personas.select{|p| p.viejo?}
  end
end

describe "Mocks" do
  include Aserciones
  class Object
    include Asertable
    include Mock
  end

  it "Se mockea regio" do
    nico = Persona.new(30)
    axel = Persona.new(30)
    lean = Persona.new(22)

    # Mockeo el mensaje para no consumir el servicio y simplificar el test
    PersonaHome.mockear(:todas_las_personas) do
      [nico, axel, lean]
    end

    viejos = PersonaHome.personas_viejas

    expect(viejos).to match_array [nico, axel]
  end

  it "Se desmockea aún mejor" do
    nico = Persona.new(30)
    axel = Persona.new(30)
    lean = Persona.new(22)

    # Mockeo el mensaje para no consumir el servicio y simplificar el test
    PersonaHome.mockear(:todas_las_personas) do
      [nico, axel, lean]
    end

    viejos = PersonaHome.personas_viejas

    Mock.restaurar

    expect(viejos).not_to match_array [nico, axel]
  end
end
