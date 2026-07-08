import 'dart:math';
import 'package:flutter/material.dart';

class NetworkToolsPage extends StatefulWidget {
  const NetworkToolsPage({super.key});

  @override
  State<NetworkToolsPage> createState() => _NetworkToolsPageState();
}

class _NetworkToolsPageState extends State<NetworkToolsPage> {
  int _tabIndex = 0;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(title: const Text('网络工具')),
      body: Column(
        children: [
          TabBar(
            tabs: const [
              Tab(text: '网络状态'),
              Tab(text: '实时延迟'),
              Tab(text: '连接状态'),
            ],
            onTap: (i) => setState(() => _tabIndex = i),
            controller: TabController(length: 3, vsync: this, initialIndex: _tabIndex)..addListener(() {
              if (mounted) setState(() {});
            }),
          ),
          Expanded(child: _buildTabContent(theme)),
        ],
      ),
    );
  }

  Widget _buildTabContent(ThemeData theme) {
    switch (_tabIndex) {
      case 0:
        return _buildNetworkStatus(theme);
      case 1:
        return _buildPingGraph(theme);
      case 2:
        return _buildConnections(theme);
      default:
        return const SizedBox.shrink();
    }
  }

  Widget _buildNetworkStatus(ThemeData theme) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        Card(
          child: Padding(
            padding: const EdgeInsets.all(16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Icon(Icons.wifi, color: theme.colorScheme.primary),
                    const SizedBox(width: 8),
                    Text('网络接口', style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
                  ],
                ),
                const Divider(),
                _infoRow('接口', 'wlan0'),
                _infoRow('类型', 'WiFi 5GHz'),
                _infoRow('IP', '192.168.1.100'),
                _infoRow('网关', '192.168.1.1'),
                _infoRow('DNS', '8.8.8.8'),
              ],
            ),
          ),
        ),
      ],
    );
  }

  Widget _buildPingGraph(ThemeData theme) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        Card(
          child: Padding(
            padding: const EdgeInsets.all(16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Icon(Icons.timeline, color: theme.colorScheme.primary),
                    const SizedBox(width: 8),
                    Text('延迟折线图', style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
                  ],
                ),
                const SizedBox(height: 16),
                SizedBox(
                  height: 200,
                  child: CustomPaint(
                    size: const Size(double.infinity, 200),
                    painter: _PingGraphPainter(),
                  ),
                ),
                const SizedBox(height: 12),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    _statItem('平均', '35ms', Colors.green),
                    _statItem('最低', '12ms', Colors.blue),
                    _statItem('最高', '89ms', Colors.orange),
                    _statItem('丢包率', '0%', Colors.green),
                  ],
                ),
              ],
            ),
          ),
        ),
      ],
    );
  }

  Widget _buildConnections(ThemeData theme) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        Card(
          child: Padding(
            padding: const EdgeInsets.all(16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Icon(Icons.link, color: theme.colorScheme.primary),
                    const SizedBox(width: 8),
                    Text('TCP 连接', style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
                  ],
                ),
                const Divider(),
                _connectionRow(theme, '192.168.1.1:80', 'ESTABLISHED', Colors.green),
                _connectionRow(theme, '8.8.8.8:53', 'ESTABLISHED', Colors.green),
                _connectionRow(theme, '1.1.1.1:443', 'TIME_WAIT', Colors.orange),
              ],
            ),
          ),
        ),
      ],
    );
  }

  Widget _infoRow(String label, String value) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4),
      child: Row(
        children: [
          SizedBox(width: 80, child: Text(label, style: const TextStyle(fontWeight: FontWeight.w500))),
          Expanded(child: Text(value, textAlign: TextAlign.end)),
        ],
      ),
    );
  }

  Widget _connectionRow(ThemeData theme, String address, String status, Color statusColor) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4),
      child: Row(
        children: [
          Expanded(child: Text(address)),
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 2),
            decoration: BoxDecoration(
              color: statusColor.withOpacity(0.15),
              borderRadius: BorderRadius.circular(4),
            ),
            child: Text(status, style: TextStyle(fontSize: 12, color: statusColor)),
          ),
        ],
      ),
    );
  }

  Widget _statItem(String label, String value, Color color) {
    return Column(
      children: [
        Text(value, style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold, color: color)),
        Text(label, style: const TextStyle(fontSize: 12)),
      ],
    );
  }
}

class _PingGraphPainter extends CustomPainter {
  final List<double> data = [25, 18, 35, 22, 40, 15, 28, 33, 20, 45, 12, 30, 38, 19, 42, 27, 15, 50, 28, 35];

  @override
  void paint(Canvas canvas, Size size) {
    if (data.isEmpty) return;

    final paint = Paint()
      ..color = Colors.blue
      ..strokeWidth = 2
      ..style = PaintingStyle.stroke;

    final fillPaint = Paint()
      ..shader = LinearGradient(
        begin: Alignment.topCenter,
        end: Alignment.bottomCenter,
        colors: [Colors.blue.withOpacity(0.3), Colors.blue.withOpacity(0.0)],
      ).createShader(Rect.fromLTWH(0, 0, size.width, size.height));

    final path = Path();
    final fillPath = Path();
    final stepX = size.width / (data.length - 1);
    final maxVal = data.reduce(max);
    final minVal = data.reduce(min);
    final range = maxVal - minVal > 0 ? maxVal - minVal : 1.0;

    for (int i = 0; i < data.length; i++) {
      final x = i * stepX;
      final y = size.height - ((data[i] - minVal) / range * (size.height - 20)) - 10;
      if (i == 0) {
        path.moveTo(x, y);
        fillPath.moveTo(x, y);
      } else {
        path.lineTo(x, y);
        fillPath.lineTo(x, y);
      }
    }

    fillPath.lineTo(size.width, size.height);
    fillPath.lineTo(0, size.height);
    fillPath.close();

    canvas.drawPath(fillPath, fillPaint);
    canvas.drawPath(path, paint);

    // 画点
    for (int i = 0; i < data.length; i++) {
      final x = i * stepX;
      final y = size.height - ((data[i] - minVal) / range * (size.height - 20)) - 10;
      canvas.drawCircle(Offset(x, y), 2, Paint()..color = Colors.blue);
    }
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) => true;
}
