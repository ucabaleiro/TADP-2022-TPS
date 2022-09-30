require_relative './helpers'

class PersonaHome
  def todas_las_personas
    ["jusepe","jusepa"]
    # Este método consume un servicio web que consulta una base de datos
  end

  def personas_viejas
    self.todas_las_personas.select{|p| p.viejo?}
  end
end


describe "Mocks" do
  before do
    TADsPec.incluir Mockeable
  end

  after do
    TADsPec.excluir Mockeable
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

    Mockeable.restaurar

    viejos = PersonaHome.new.personas_viejas

    expect(viejos).not_to match_array [nico, axel]
  end
end
