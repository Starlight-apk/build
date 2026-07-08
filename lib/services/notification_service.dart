import 'package:flutter/material.dart';

class NotificationService extends ChangeNotifier {
  final List<_NotificationItem> _notifications = [];
  static const int _maxNotes = 5;

  List<_NotificationItem> get notifications => List.unmodifiable(_notifications);

  void show(String message) {
    if (_notifications.length >= _maxNotes) {
      _notifications.removeAt(0);
    }
    final note = _NotificationItem(message: message, createdAt: DateTime.now());
    _notifications.add(note);
    notifyListeners();
    Future.delayed(const Duration(seconds: 5), () {
      _notifications.remove(note);
      notifyListeners();
    });
  }
}

class _NotificationItem {
  final String message;
  final DateTime createdAt;
  bool exiting = false;

  _NotificationItem({required this.message, required this.createdAt});
}
