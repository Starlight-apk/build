import 'package:flutter/material.dart';
import 'pages/home_page.dart';
import 'pages/tools_page.dart';
import 'pages/settings_page.dart';
import 'pages/about_page.dart';

void main() {
  runApp(const StarToolApp());
}

class StarToolApp extends StatelessWidget {
  const StarToolApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'StarTool',
      theme: ThemeData(
        colorSchemeSeed: const Color(0xFF3f51b5),
        useMaterial3: true,
        brightness: Brightness.dark,
      ),
      home: const MainScreen(),
      debugShowCheckedModeBanner: false,
    );
  }
}

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int _currentIndex = 0;

  final List<Widget> _pages = const [
    HomePage(),
    ToolsPage(),
    SettingsPage(),
    AboutPage(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _pages[_currentIndex],
      bottomNavigationBar: NavigationBar(
        selectedIndex: _currentIndex,
        onDestinationSelected: (i) => setState(() => _currentIndex = i),
        destinations: const [
          NavigationDestination(icon: Icon(Icons.dashboard_outlined), selectedIcon: Icon(Icons.dashboard), label: '概览'),
          NavigationDestination(icon: Icon(Icons.build_outlined), selectedIcon: Icon(Icons.build), label: '工具'),
          NavigationDestination(icon: Icon(Icons.settings_outlined), selectedIcon: Icon(Icons.settings), label: '设置'),
          NavigationDestination(icon: Icon(Icons.info_outline), selectedIcon: Icon(Icons.info), label: '关于'),
        ],
      ),
    );
  }
}
