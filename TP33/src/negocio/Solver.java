package negocio;

import java.util.ArrayList;

public class Solver 
{
	ArrayList<Materia> _materias;
    ArrayList<Aula> _aulas;
    ArrayList<Aula> _aulasDisponibles;
    ArrayList<Espectador> _espectadores;
	
	public Solver(ArrayList<Materia> materias)
	{
		_materias = materias;
		_aulas = new ArrayList<>();
		_aulasDisponibles = new ArrayList<>();
		_espectadores = new ArrayList<>();
		
		Aula a = new Aula(_aulas.size());
		_aulas.add(a);
		_aulasDisponibles.add(a);
	}
	
	public void agregarEspectador(Espectador e){
		
		_espectadores.add(e);
	}
	
	public void asignarAulas()
	{			
		for(Materia materia : _materias)
		{
			ubicarEnAula(materia);
			notificarEspectadores();
		}
	}
	
	private void ubicarEnAula(Materia materia){
		
		int i = 0;
		
		while(!(_aulasDisponibles.get(i).asignar(materia)))
		{
			i++;
			
			if(i >= _aulasDisponibles.size())
			{
				Aula a = new Aula(_aulas.size());
				_aulas.add(a);
				_aulasDisponibles.add(a);
			}
		}
		
		Aula a = _aulasDisponibles.get(i);
		
		if(a.estaLlena()) _aulasDisponibles.remove(a);
	}

	private void notificarEspectadores(){
		
		for(Espectador e : _espectadores){
			
			e.notificar();
		}
	}
}
