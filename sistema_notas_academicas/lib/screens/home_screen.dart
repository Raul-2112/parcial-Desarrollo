import 'package:flutter/material.dart';
import '../models/materia.dart';
import '../models/gestor_materias.dart';
import 'agregar_materia_screen.dart';
import 'detalle_materia_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final GestorMaterias _gestorMaterias = GestorMaterias();

  void _agregarMateria() async {
    final result = await Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => const AgregarMateriaScreen(),
      ),
    );

    if (result != null && result is Materia) {
      setState(() {
        _gestorMaterias.agregarMateria(result);
      });
    }
  }

  void _eliminarMateria(int index) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Confirmar eliminación'),
        content: Text(
          '¿Está seguro de eliminar la materia "${_gestorMaterias.materias[index].nombre}"?',
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Cancelar'),
          ),
          TextButton(
            onPressed: () {
              setState(() {
                _gestorMaterias.eliminarMateria(index);
              });
              Navigator.pop(context);
            },
            child: const Text('Eliminar', style: TextStyle(color: Colors.red)),
          ),
        ],
      ),
    );
  }

  void _verDetalleMateria(int index) async {
    await Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => DetalleMateriaScreen(
          materia: _gestorMaterias.materias[index],
        ),
      ),
    );
    setState(() {}); // Actualizar la UI al regresar
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Sistema de Notas Académicas'),
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
        elevation: 2,
      ),
      body: Column(
        children: [
          // Estadísticas generales
          if (_gestorMaterias.materias.isNotEmpty)
            Container(
              width: double.infinity,
              margin: const EdgeInsets.all(16),
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                color: Colors.blue.shade50,
                borderRadius: BorderRadius.circular(12),
                border: Border.all(color: Colors.blue.shade200),
              ),
              child: Column(
                children: [
                  const Text(
                    'Resumen General',
                    style: TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                      color: Colors.blue,
                    ),
                  ),
                  const SizedBox(height: 12),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      _buildEstadistica(
                        'Total',
                        '${_gestorMaterias.materias.length}',
                        Colors.blue,
                      ),
                      _buildEstadistica(
                        'Aprobadas',
                        '${_gestorMaterias.contarMateriasAprobadas()}',
                        Colors.green,
                      ),
                      _buildEstadistica(
                        'Reprobadas',
                        '${_gestorMaterias.contarMateriasReprobadas()}',
                        Colors.red,
                      ),
                    ],
                  ),
                  if (_gestorMaterias.materias.any((m) => m.notas.isNotEmpty))
                    Padding(
                      padding: const EdgeInsets.only(top: 12),
                      child: Text(
                        'Promedio General: ${_gestorMaterias.calcularPromedioGeneral().toStringAsFixed(2)}',
                        style: const TextStyle(
                          fontSize: 16,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                ],
              ),
            ),

          // Lista de materias
          Expanded(
            child: _gestorMaterias.materias.isEmpty
                ? Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Icon(
                          Icons.school_outlined,
                          size: 80,
                          color: Colors.grey.shade400,
                        ),
                        const SizedBox(height: 16),
                        Text(
                          'No hay materias registradas',
                          style: TextStyle(
                            fontSize: 18,
                            color: Colors.grey.shade600,
                          ),
                        ),
                        const SizedBox(height: 8),
                        Text(
                          'Presiona el botón + para agregar una',
                          style: TextStyle(
                            fontSize: 14,
                            color: Colors.grey.shade500,
                          ),
                        ),
                      ],
                    ),
                  )
                : ListView.builder(
                    padding: const EdgeInsets.symmetric(horizontal: 16),
                    itemCount: _gestorMaterias.materias.length,
                    itemBuilder: (context, index) {
                      final materia = _gestorMaterias.materias[index];
                      final promedio = materia.calcularPromedio();
                      final estado = materia.obtenerEstado();

                      return Card(
                        margin: const EdgeInsets.only(bottom: 12),
                        elevation: 2,
                        child: ListTile(
                          leading: CircleAvatar(
                            backgroundColor: estado == 'Aprobada'
                                ? Colors.green
                                : estado == 'Reprobada'
                                    ? Colors.red
                                    : Colors.orange,
                            child: Text(
                              materia.nombre[0].toUpperCase(),
                              style: const TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                          title: Text(
                            materia.nombre,
                            style: const TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 16,
                            ),
                          ),
                          subtitle: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              if (materia.creditos != null)
                                Text('Créditos: ${materia.creditos}'),
                              Text('Notas: ${materia.notas.length}'),
                              if (materia.notas.isNotEmpty)
                                Text(
                                  'Promedio: ${promedio.toStringAsFixed(2)} - $estado',
                                  style: TextStyle(
                                    color: estado == 'Aprobada'
                                        ? Colors.green
                                        : estado == 'Reprobada'
                                            ? Colors.red
                                            : Colors.orange,
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                            ],
                          ),
                          trailing: IconButton(
                            icon: const Icon(Icons.delete, color: Colors.red),
                            onPressed: () => _eliminarMateria(index),
                          ),
                          onTap: () => _verDetalleMateria(index),
                        ),
                      );
                    },
                  ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _agregarMateria,
        backgroundColor: Colors.blue,
        child: const Icon(Icons.add, color: Colors.white),
      ),
    );
  }

  Widget _buildEstadistica(String label, String valor, Color color) {
    return Column(
      children: [
        Text(
          valor,
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
            color: color,
          ),
        ),
        Text(
          label,
          style: TextStyle(
            fontSize: 12,
            color: Colors.grey.shade700,
          ),
        ),
      ],
    );
  }
}
