import 'package:flutter/material.dart';
import 'screens/home_screen.dart';

void main() {
  runApp(const SistemaNotasApp());
}

class SistemaNotasApp extends StatelessWidget {
  const SistemaNotasApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Sistema de Notas Académicas',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
        appBarTheme: const AppBarTheme(
          centerTitle: true,
        ),
      ),
      home: const HomeScreen(),
    );
  }
}
