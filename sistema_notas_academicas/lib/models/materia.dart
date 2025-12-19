class Materia {
  String nombre;
  int? creditos;
  List<double> notas;

  Materia({
    required this.nombre,
    this.creditos,
    List<double>? notas,
  }) : notas = notas ?? [];

  // Método para agregar una nota
  void agregarNota(double nota) {
    if (nota >= 0.0 && nota <= 5.0) {
      notas.add(nota);
    } else {
      throw ArgumentError('La nota debe estar entre 0.0 y 5.0');
    }
  }

  // Método para eliminar una nota por índice
  void eliminarNota(int index) {
    if (index >= 0 && index < notas.length) {
      notas.removeAt(index);
    }
  }

  // Método para calcular el promedio
  double calcularPromedio() {
    if (notas.isEmpty) return 0.0;
    double suma = notas.reduce((a, b) => a + b);
    return suma / notas.length;
  }

  // Método para verificar si está aprobada
  bool estaAprobada() {
    return calcularPromedio() >= 3.0;
  }

  // Método para obtener el estado de la materia
  String obtenerEstado() {
    if (notas.length < 3) {
      return 'Incompleto';
    }
    return estaAprobada() ? 'Aprobada' : 'Reprobada';
  }

  // Método para validar si tiene notas suficientes
  bool tieneNotasSuficientes() {
    return notas.length >= 3;
  }
}
