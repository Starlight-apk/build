import 'package:flutter/material.dart';
import 'pages/home_page.dart';
import 'pages/tools_page.dart';
import 'pages/settings_page.dart';
import 'pages/about_page.dart';
import 'services/notification_service.dart';
import 'services/settings_service.dart';

final NotificationService notificationService = NotificationService();
final SettingsService settingsService = SettingsService();

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await settingsService.load();
  runApp(const StarToolApp());
}

class StarToolApp extends StatelessWidget {
  const StarToolApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ListenableBuilder(
      listenable: settingsService,
      builder: (context, _) {
        return MaterialApp(
          title: 'StarTool',
          theme: ThemeData(
            colorSchemeSeed: settingsService.seedColor,
            useMaterial3: true,
            brightness: settingsService.darkMode ? Brightness.dark : Brightness.light,
          ),
          home: const MainScreen(),
          debugShowCheckedModeBanner: false,
        );
      },
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
    final isWide = MediaQuery.of(context).size.width >= 768;
    final bottomNav = NavigationBar(
      selectedIndex: _currentIndex,
      onDestinationSelected: (i) => setState(() => _currentIndex = i),
      destinations: const [
        NavigationDestination(icon: Icon(Icons.dashboard_outlined), selectedIcon: Icon(Icons.dashboard), label: '概览'),
        NavigationDestination(icon: Icon(Icons.build_outlined), selectedIcon: Icon(Icons.build), label: '工具'),
        NavigationDestination(icon: Icon(Icons.settings_outlined), selectedIcon: Icon(Icons.settings), label: '设置'),
        NavigationDestination(icon: Icon(Icons.info_outline), selectedIcon: Icon(Icons.info), label: '关于'),
      ],
    );

    if (isWide) {
      return Scaffold(
        body: Row(
          children: [
            NavigationRail(
              selectedIndex: _currentIndex,
              onDestinationSelected: (i) => setState(() => _currentIndex = i),
              labelType: NavigationRailLabelType.all,
              leading: Padding(
                padding: const EdgeInsets.symmetric(vertical: 8),
                child: Icon(Icons.auto_awesome, color: Theme.of(context).colorScheme.primary, size: 32),
              ),
              destinations: const [
                NavigationRailDestination(icon: Icon(Icons.dashboard_outlined), selectedIcon: Icon(Icons.dashboard), label: Text('概览')),
                NavigationRailDestination(icon: Icon(Icons.build_outlined), selectedIcon: Icon(Icons.build), label: Text('工具')),
                NavigationRailDestination(icon: Icon(Icons.settings_outlined), selectedIcon: Icon(Icons.settings), label: Text('设置')),
                NavigationRailDestination(icon: Icon(Icons.info_outline), selectedIcon: Icon(Icons.info), label: Text('关于')),
              ],
            ),
            const VerticalDivider(width: 1),
            Expanded(child: _pages[_currentIndex]),
          ],
        ),
        bottomNavigationBar: null,
      );
    }

    return Scaffold(
      body: Stack(
        children: [
          _pages[_currentIndex],
          ListenableBuilder(
            listenable: notificationService,
            builder: (context, _) {
              final notes = notificationService.notifications;
              if (notes.isEmpty) return const SizedBox.shrink();
              return Positioned(
                left: 16,
                bottom: 80,
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: notes.map((note) {
                    return AnimatedContainer(
                      duration: const Duration(milliseconds: 200),
                      margin: const EdgeInsets.only(bottom: 8),
                      child: Material(
                        elevation: 3,
                        borderRadius: BorderRadius.circular(8),
                        color: Colors.white,
                        child: Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
                          child: Text(note.message, style: const TextStyle(color: Colors.black, fontSize: 14)),
                        ),
                      ),
                    );
                  }).toList(),
                ),
              );
            },
          ),
        ],
      ),
      bottomNavigationBar: bottomNav,
    );
  }
}
