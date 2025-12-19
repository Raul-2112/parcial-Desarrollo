# Programación Orientada a Objetos en Sistema de Notas Académicas

## 📚 Conceptos de POO Implementados

Este documento explica cómo se aplicaron los principios de Programación Orientada a Objetos en el proyecto.

---

## 1. Clases y Objetos

### Clase `Materia`
Representa la abstracción de una materia académica del mundo real.

```dart
class Materia {
  String nombre;           // Atributo
  int? creditos;          // Atributo opcional
  List<double> notas;     // Colección de notas
  
  // Constructor
  Materia({
    required this.nombre,
    this.creditos,
    List<double>? notas,
  }) : notas = notas ?? [];
  
  // Métodos
  void agregarNota(double nota) { ... }
  double calcularPromedio() { ... }
  bool estaAprobada() { ... }
}
```

**Objetos**: Cada materia creada es una instancia de la clase Materia
```dart
Materia matematicas = Materia(nombre: "Matemáticas", creditos: 4);
Materia fisica = Materia(nombre: "Física", creditos: 3);
```

---

## 2. Encapsulamiento

### Ocultamiento de Información

La clase `GestorMaterias` encapsula la lista de materias como atributo privado:

```dart
class GestorMaterias {
  final List<Materia> _materias = [];  // Privado (guion bajo)
  
  // Acceso controlado mediante getter
  List<Materia> get materias => List.unmodifiable(_materias);
}
```

**Ventajas**:
- ✅ No se puede modificar directamente `_materias` desde fuera
- ✅ Solo se accede mediante métodos controlados
- ✅ Protege la integridad de los datos

### Métodos Públicos vs Privados

```dart
class Materia {
  // Método público - puede ser llamado externamente
  double calcularPromedio() {
    if (notas.isEmpty) return 0.0;
    return _sumarNotas() / notas.length;  // Usa método privado
  }
  
  // Método privado - solo para uso interno
  double _sumarNotas() {
    return notas.reduce((a, b) => a + b);
  }
}
```

---

## 3. Abstracción

### Separación de Responsabilidades

**Capa de Lógica de Negocio** (models/)
```dart
// materia.dart - Solo lógica de cálculo
class Materia {
  double calcularPromedio() { ... }
  bool estaAprobada() { ... }
}
```

**Capa de Presentación** (screens/)
```dart
// home_screen.dart - Solo interfaz de usuario
class HomeScreen extends StatefulWidget {
  void _mostrarMaterias() { ... }
  void _navegarADetalle() { ... }
}
```

**Beneficio**: El usuario de la clase no necesita saber CÓMO se calcula el promedio, solo que existe el método.

---

## 4. Métodos y Comportamientos

### Métodos de Instancia

Operan sobre los datos de un objeto específico:

```dart
class Materia {
  List<double> notas = [];
  
  // Opera sobre las notas de ESTA materia
  void agregarNota(double nota) {
    if (nota >= 0.0 && nota <= 5.0) {
      notas.add(nota);
    }
  }
  
  // Calcula el promedio de ESTA materia
  double calcularPromedio() {
    return notas.reduce((a, b) => a + b) / notas.length;
  }
}
```

### Métodos con Retorno de Valores

```dart
// Retorna un booleano
bool estaAprobada() {
  return calcularPromedio() >= 3.0;
}

// Retorna un String
String obtenerEstado() {
  if (notas.length < 3) return 'Incompleto';
  return estaAprobada() ? 'Aprobada' : 'Reprobada';
}

// Retorna un double
double calcularPromedio() {
  if (notas.isEmpty) return 0.0;
  return notas.reduce((a, b) => a + b) / notas.length;
}
```

---

## 5. Constructor

### Constructor Nombrado con Parámetros

```dart
class Materia {
  String nombre;
  int? creditos;
  List<double> notas;
  
  // Constructor con parámetros nombrados
  Materia({
    required this.nombre,      // Obligatorio
    this.creditos,             // Opcional
    List<double>? notas,       // Opcional con valor por defecto
  }) : notas = notas ?? [];    // Lista de inicialización
}
```

**Uso**:
```dart
// Todos los parámetros
Materia m1 = Materia(
  nombre: "Programación", 
  creditos: 4, 
  notas: [4.0, 3.5]
);

// Solo obligatorios
Materia m2 = Materia(nombre: "Inglés");
```

---

## 6. Composición

Una clase contiene objetos de otra clase:

```dart
class GestorMaterias {
  // Composición: GestorMaterias TIENE Materias
  final List<Materia> _materias = [];
  
  void agregarMateria(Materia materia) {
    _materias.add(materia);
  }
}
```

**Relación "tiene-un"**: 
- Un GestorMaterias TIENE muchas Materias
- Una Materia TIENE muchas notas

---

## 7. Validación de Datos

### Validación en Métodos

```dart
void agregarNota(double nota) {
  // Validación de rango
  if (nota >= 0.0 && nota <= 5.0) {
    notas.add(nota);
  } else {
    throw ArgumentError('La nota debe estar entre 0.0 y 5.0');
  }
}
```

### Validación en Constructores

```dart
class Materia {
  String nombre;
  
  Materia({required this.nombre}) {
    // Validación en constructor
    if (nombre.trim().isEmpty) {
      throw ArgumentError('El nombre no puede estar vacío');
    }
  }
}
```

---

## 8. Principio de Responsabilidad Única

Cada clase tiene UNA razón para cambiar:

### ✅ Buena Práctica

```dart
// Clase Materia: Solo gestiona UNA materia
class Materia {
  void agregarNota(double nota) { ... }
  double calcularPromedio() { ... }
}

// Clase GestorMaterias: Solo gestiona la COLECCIÓN
class GestorMaterias {
  void agregarMateria(Materia materia) { ... }
  double calcularPromedioGeneral() { ... }
}
```

### ❌ Mala Práctica

```dart
// Clase que hace DEMASIADO
class SistemaCompleto {
  void agregarMateria() { ... }
  void calcularPromedio() { ... }
  void mostrarEnPantalla() { ... }  // No debería estar aquí
  void guardarEnBaseDatos() { ... }  // No debería estar aquí
}
```

---

## 9. Lógica de Negocio vs Interfaz

### Lógica de Negocio (Models)

```dart
// models/materia.dart
class Materia {
  // SOLO lógica de cálculo
  double calcularPromedio() {
    if (notas.isEmpty) return 0.0;
    return notas.reduce((a, b) => a + b) / notas.length;
  }
  
  bool estaAprobada() {
    return calcularPromedio() >= 3.0;
  }
}
```

### Interfaz Gráfica (Screens)

```dart
// screens/detalle_materia_screen.dart
class DetalleMateriaScreen extends StatefulWidget {
  // SOLO interfaz de usuario
  Widget build(BuildContext context) {
    return Text('Promedio: ${materia.calcularPromedio()}');
  }
}
```

**Beneficio**: Si cambio cómo calculo el promedio, no afecta la interfaz.

---

## 10. Getters y Setters

### Getter Público

```dart
class GestorMaterias {
  final List<Materia> _materias = [];
  
  // Getter: acceso de solo lectura
  List<Materia> get materias => List.unmodifiable(_materias);
}
```

**Uso**:
```dart
GestorMaterias gestor = GestorMaterias();
print(gestor.materias.length);  // Lectura OK
gestor.materias.add(...);        // Error: lista inmutable
```

### Propiedades Calculadas

```dart
class Materia {
  List<double> notas = [];
  
  // Propiedad calculada
  int get cantidadNotas => notas.length;
  
  // Propiedad calculada compleja
  String get estado {
    if (notas.length < 3) return 'Incompleto';
    return calcularPromedio() >= 3.0 ? 'Aprobada' : 'Reprobada';
  }
}
```

---

## 11. Inmutabilidad Parcial

```dart
class GestorMaterias {
  // Lista privada y final
  final List<Materia> _materias = [];
  
  // Retorna copia inmutable
  List<Materia> get materias => List.unmodifiable(_materias);
}
```

**Ventajas**:
- ✅ Nadie puede modificar la lista directamente
- ✅ Solo se modifica mediante métodos controlados
- ✅ Mayor seguridad y previsibilidad

---

## 12. Manejo de Valores Nulos

### Parámetros Opcionales

```dart
class Materia {
  String nombre;
  int? creditos;  // Puede ser null
  
  Materia({
    required this.nombre,
    this.creditos,  // Opcional
  });
}
```

### Operador ??

```dart
// Lista de inicialización con valor por defecto
Materia({
  List<double>? notas,
}) : notas = notas ?? [];  // Si es null, usa lista vacía
```

### Operador ?.

```dart
// Acceso seguro a propiedades
if (materia.creditos != null) {
  print('Créditos: ${materia.creditos}');
}

// O más corto:
print('Créditos: ${materia.creditos ?? "No especificado"}');
```

---

## 🎯 Resumen de Beneficios de POO

### 1. **Organización**
- Código estructurado en clases con responsabilidades claras
- Fácil de navegar y entender

### 2. **Reutilización**
- Las clases pueden usarse en diferentes partes del proyecto
- Menos código duplicado

### 3. **Mantenibilidad**
- Cambios localizados en una clase
- No afecta otras partes del código

### 4. **Escalabilidad**
- Fácil agregar nuevas funcionalidades
- Extender clases existentes

### 5. **Abstracción**
- Oculta complejidad interna
- Interfaces simples y claras

---

## 📊 Diagrama de Clases

```
┌─────────────────────────────┐
│       GestorMaterias        │
├─────────────────────────────┤
│ - _materias: List<Materia>  │
├─────────────────────────────┤
│ + agregarMateria()          │
│ + eliminarMateria()         │
│ + calcularPromedioGeneral() │
└────────────┬────────────────┘
             │ contiene
             │ 0..*
             ▼
┌─────────────────────────────┐
│          Materia            │
├─────────────────────────────┤
│ + nombre: String            │
│ + creditos: int?            │
│ + notas: List<double>       │
├─────────────────────────────┤
│ + agregarNota()             │
│ + eliminarNota()            │
│ + calcularPromedio()        │
│ + estaAprobada()            │
└─────────────────────────────┘
```

---

## 💡 Conclusión

Este proyecto demuestra cómo aplicar POO de manera práctica:

✅ **Clases bien diseñadas** con responsabilidades claras
✅ **Encapsulamiento** protegiendo datos sensibles  
✅ **Abstracción** separando lógica de UI
✅ **Métodos** que encapsulan comportamiento
✅ **Validación** en los lugares correctos
✅ **Código mantenible** y escalable

La POO no es solo teoría, es una herramienta poderosa para escribir código de calidad profesional.
