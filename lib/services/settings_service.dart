import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SettingsService extends ChangeNotifier {
  bool _darkMode = true;
  Color _seedColor = const Color(0xFF3f51b5);
  bool _loaded = false;

  bool get darkMode => _darkMode;
  Color get seedColor => _seedColor;
  bool get loaded => _loaded;

  Future<void> load() async {
    final prefs = await SharedPreferences.getInstance();
    _darkMode = prefs.getBool('darkMode') ?? true;
    final colorValue = prefs.getInt('seedColor') ?? 0xFF3f51b5;
    _seedColor = Color(colorValue);
    _loaded = true;
    notifyListeners();
  }

  Future<void> setDarkMode(bool value) async {
    _darkMode = value;
    notifyListeners();
    final prefs = await SharedPreferences.getInstance();
    await prefs.setBool('darkMode', value);
  }

  Future<void> setSeedColor(Color color) async {
    _seedColor = color;
    notifyListeners();
    final prefs = await SharedPreferences.getInstance();
    await prefs.setInt('seedColor', color.value);
  }
}
