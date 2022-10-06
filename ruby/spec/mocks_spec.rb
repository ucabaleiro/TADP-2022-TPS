require_relative './helpers'

class PersonaHome
  def todas_las_personas
    [] # Este método consume un servicio web que consulta una base de datos
  end

  def personas_viejas
    self.todas_las_personas.select{|p| p.viejo?}
  end
end

module Nombrable
  attr_reader :nombre

  def initialize(nombre)
    @nombre = nombre
  end
end

describe "Mocks" do
  let(:clase_nombrable) do
    Class.new do
      include Nombrable
    end
  end

  before do
    TADsPec.configurar_en self
  end

  after do
    TADsPec.revertir
  end

  it "Se mockea regio" do
    nico = Persona.new(30)
    axel = Persona.new(30)
    lean = Persona.new(22)

    # Mockeo el mensaje para no consumir el servicio y simplificar el test
    PersonaHome.mockear(:todas_las_personas) do
      [nico, axel, lean]
    end

    viejos = PersonaHome.new.personas_viejas

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

    Cambios.revertir

    viejos = PersonaHome.new.personas_viejas

    expect(viejos).not_to match_array [nico, axel]
  end

  it "También se mockea con mixins" do
    nico = clase_nombrable.new("Nico")

    Nombrable.mockear(:nombre) do
      "Giusepe"
    end

    expect(nico.nombre).to eq "Giusepe"
  end

  it "También se desmockea con mixins" do
    nico = clase_nombrable.new("Nico")

    Nombrable.mockear(:nombre) do
      "Giusepa"
    end

    Cambios.revertir

    expect(nico.nombre).to eq "Nico"
  end
end
