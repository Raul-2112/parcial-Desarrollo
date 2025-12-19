import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import '../models/materia.dart';

class AgregarMateriaScreen extends StatefulWidget {
  const AgregarMateriaScreen({super.key});

  @override
  State<AgregarMateriaScreen> createState() => _AgregarMateriaScreenState();
}

class _AgregarMateriaScreenState extends State<AgregarMateriaScreen> {
  final _formKey = GlobalKey<FormState>();
  final _nombreController = TextEditingController();
  final _creditosController = TextEditingController();

  @override
  void dispose() {
    _nombreController.dispose();
    _creditosController.dispose();
    super.dispose();
  }

  void _guardarMateria() {
    if (_formKey.currentState!.validate()) {
      final nombre = _nombreController.text.trim();
      final creditos = _creditosController.text.trim().isEmpty
          ? null
          : int.parse(_creditosController.text.trim());

      final materia = Materia(
        nombre: nombre,
        creditos: creditos,
      );

      Navigator.pop(context, materia);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Agregar Materia'),
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              const Icon(
                Icons.book,
                size: 80,
                color: Colors.blue,
              ),
              const SizedBox(height: 24),
              TextFormField(
                controller: _nombreController,
                decoration: InputDecoration(
                  labelText: 'Nombre de la materia *',
                  hintText: 'Ej: Matemáticas',
                  prefixIcon: const Icon(Icons.subject),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                ),
                validator: (value) {
                  if (value == null || value.trim().isEmpty) {
                    return 'Por favor ingrese el nombre de la materia';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16),
              TextFormField(
                controller: _creditosController,
                decoration: InputDecoration(
                  labelText: 'Créditos (opcional)',
                  hintText: 'Ej: 3',
                  prefixIcon: const Icon(Icons.stars),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                ),
                keyboardType: TextInputType.number,
                inputFormatters: [
                  FilteringTextInputFormatter.digitsOnly,
                ],
                validator: (value) {
                  if (value != null && value.trim().isNotEmpty) {
                    final creditos = int.tryParse(value.trim());
                    if (creditos == null || creditos <= 0) {
                      return 'Los créditos deben ser un número positivo';
                    }
                  }
                  return null;
                },
              ),
              const SizedBox(height: 24),
              ElevatedButton(
                onPressed: _guardarMateria,
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.blue,
                  foregroundColor: Colors.white,
                  padding: const EdgeInsets.symmetric(vertical: 16),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                ),
                child: const Text(
                  'Guardar Materia',
                  style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                ),
              ),
              const SizedBox(height: 12),
              TextButton(
                onPressed: () => Navigator.pop(context),
                child: const Text('Cancelar'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
