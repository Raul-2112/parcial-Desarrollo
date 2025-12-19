# Sistema de Notas Académicas

Aplicación móvil desarrollada en Flutter para gestionar materias y sus notas parciales, con cálculo automático de promedios.

## 📋 Características

### Gestión de Materias
- ✅ Registrar materias con nombre y créditos (opcional)
- ✅ Visualizar lista de materias registradas
- ✅ Eliminar materias con confirmación
- ✅ Ver resumen general (total, aprobadas, reprobadas)
- ✅ Calcular promedio general de todas las materias

### Gestión de Notas
- ✅ Registrar múltiples notas por materia (mínimo 3)
- ✅ Validación de rango 0.0 - 5.0
- ✅ Visualizar todas las notas de una materia
- ✅ Eliminar notas individuales

### Cálculo Automático
- ✅ Promedio automático por materia
- ✅ Estado de la materia:
  - **Aprobada**: promedio ≥ 3.0
  - **Reprobada**: promedio < 3.0
  - **Incompleto**: menos de 3 notas
- ✅ Promedio general de todas las materias

## 🏗️ Arquitectura

El proyecto implementa **Programación Orientada a Objetos (POO)** con separación clara de responsabilidades:

```
lib/
├── models/                    # Lógica de negocio
│   ├── materia.dart          # Clase Materia
│   └── gestor_materias.dart  # Gestor de materias
├── screens/                   # Interfaces de usuario
│   ├── home_screen.dart      # Pantalla principal
│   ├── agregar_materia_screen.dart
│   └── detalle_materia_screen.dart
└── main.dart                 # Punto de entrada
```

### Clases Principales

#### 1. `Materia` (models/materia.dart)
Representa una materia académica con sus notas y lógica de cálculo.

**Atributos:**
- `nombre`: String
- `creditos`: int? (opcional)
- `notas`: List<double>

**Métodos:**
- `agregarNota(double nota)`: Agrega una nota validada
- `eliminarNota(int index)`: Elimina una nota por índice
- `calcularPromedio()`: Calcula el promedio de las notas
- `estaAprobada()`: Verifica si está aprobada (promedio ≥ 3.0)
- `obtenerEstado()`: Retorna el estado (Aprobada/Reprobada/Incompleto)
- `tieneNotasSuficientes()`: Verifica si tiene al menos 3 notas

#### 2. `GestorMaterias` (models/gestor_materias.dart)
Gestiona la colección de materias y estadísticas generales.

**Métodos:**
- `agregarMateria(Materia materia)`
- `eliminarMateria(int index)`
- `obtenerMateria(int index)`
- `calcularPromedioGeneral()`: Promedio de todas las materias
- `contarMateriasAprobadas()`
- `contarMateriasReprobadas()`

## 🚀 Instalación y Ejecución

### Requisitos Previos
- Flutter SDK (>=3.0.0)
- Dart SDK
- Android Studio / VS Code con extensiones de Flutter
- Emulador Android o iOS / Dispositivo físico

### Pasos de Instalación

1. **Clonar o descargar el proyecto**

2. **Instalar dependencias**
```bash
cd sistema_notas_academicas
flutter pub get
```

3. **Verificar dispositivos disponibles**
```bash
flutter devices
```

4. **Ejecutar la aplicación**
```bash
flutter run
```

Para ejecutar en un dispositivo específico:
```bash
flutter run -d <device_id>
```

5. **Compilar para producción**

Android:
```bash
flutter build apk --release
```

iOS:
```bash
flutter build ios --release
```

## 📱 Uso de la Aplicación

### Pantalla Principal
- Muestra el resumen general con estadísticas
- Lista todas las materias registradas
- Cada materia muestra: nombre, créditos, cantidad de notas, promedio y estado
- Botón **+** para agregar nueva materia

### Agregar Materia
- Ingresar nombre de la materia (obligatorio)
- Ingresar créditos (opcional)
- Validación de campos

### Detalle de Materia
- Muestra información de la materia con diseño visual según estado
- Lista de todas las notas registradas
- Botón para agregar nuevas notas
- Opción de eliminar notas individuales
- Cálculo automático del promedio

### Validaciones
- ✅ Notas entre 0.0 y 5.0
- ✅ Nombre de materia obligatorio
- ✅ Créditos deben ser números positivos
- ✅ Confirmación antes de eliminar

## 🎨 Características de UI/UX

- **Diseño Material 3**: Interfaz moderna y limpia
- **Código de colores**:
  - 🟢 Verde: Aprobada (≥ 3.0)
  - 🔴 Rojo: Reprobada (< 3.0)
  - 🟠 Naranja: Incompleto (< 3 notas)
- **Feedback visual**: Diálogos de confirmación y mensajes de error
- **Iconos intuitivos**: Facilitando la navegación
- **Responsive**: Adaptable a diferentes tamaños de pantalla

## 🔧 Tecnologías Utilizadas

- **Flutter**: Framework de UI multiplataforma
- **Dart**: Lenguaje de programación
- **Material Design 3**: Sistema de diseño
- **Gestión de estado**: setState (StatefulWidget)

## 📊 Almacenamiento de Datos

Los datos se mantienen **en memoria** durante la sesión de la aplicación. No se utiliza base de datos persistente, por lo que al cerrar la aplicación se pierden los datos.

### Posibles Mejoras Futuras
- Implementar persistencia con SQLite o Hive
- Agregar autenticación de usuarios
- Exportar reportes en PDF
- Gráficos de progreso académico
- Notificaciones de fechas de examen
- Sincronización en la nube

## 👨‍💻 Estructura del Código

### Principios de POO Aplicados

1. **Encapsulamiento**: 
   - Atributos privados en `GestorMaterias` (_materias)
   - Acceso controlado mediante getters y métodos

2. **Abstracción**:
   - Separación entre lógica de negocio (models) y UI (screens)
   - Métodos públicos ocultan la complejidad interna

3. **Responsabilidad Única**:
   - Cada clase tiene una responsabilidad clara
   - `Materia`: gestiona una materia individual
   - `GestorMaterias`: gestiona la colección
   - Screens: solo interfaz de usuario

## 📝 Notas del Desarrollador

- El proyecto cumple con todos los requisitos especificados
- Implementa validaciones robustas
- UI intuitiva y profesional
- Código limpio y bien documentado
- Fácil de extender con nuevas funcionalidades

## 📄 Licencia

Este proyecto es de código abierto y está disponible para fines educativos.

---

**Desarrollado con ❤️ usando Flutter & Dart**
