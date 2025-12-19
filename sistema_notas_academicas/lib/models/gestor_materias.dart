import 'materia.dart';

class GestorMaterias {
  final List<Materia> _materias = [];

  // Getter para obtener la lista de materias
  List<Materia> get materias => List.unmodifiable(_materias);

  // Método para agregar una materia
  void agregarMateria(Materia materia) {
    _materias.add(materia);
  }

  // Método para eliminar una materia por índice
  void eliminarMateria(int index) {
    if (index >= 0 && index < _materias.length) {
      _materias.removeAt(index);
    }
  }

  // Método para obtener una materia por índice
  Materia? obtenerMateria(int index) {
    if (index >= 0 && index < _materias.length) {
      return _materias[index];
    }
    return null;
  }

  // Método para obtener el promedio general de todas las materias
  double calcularPromedioGeneral() {
    if (_materias.isEmpty) return 0.0;
    
    List<Materia> materiasConNotas = _materias
        .where((m) => m.notas.isNotEmpty)
        .toList();
    
    if (materiasConNotas.isEmpty) return 0.0;
    
    double suma = materiasConNotas
        .map((m) => m.calcularPromedio())
        .reduce((a, b) => a + b);
    
    return suma / materiasConNotas.length;
  }

  // Método para obtener el número de materias aprobadas
  int contarMateriasAprobadas() {
    return _materias
        .where((m) => m.tieneNotasSuficientes() && m.estaAprobada())
        .length;
  }

  // Método para obtener el número de materias reprobadas
  int contarMateriasReprobadas() {
    return _materias
        .where((m) => m.tieneNotasSuficientes() && !m.estaAprobada())
        .length;
  }
}
