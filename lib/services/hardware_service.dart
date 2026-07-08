import '../models/system_info.dart';

class HardwareService {
  static SystemInfo getSystemInfo() {
    return SystemInfo(
      cpu: CpuInfo(
        model: 'ARM Cortex-A78',
        usage: 45.2,
        currentFreq: 1800.0,
        maxFreq: 2400.0,
        cores: 8,
      ),
      ram: RamInfo(
        used: 3.2,
        total: 7.5,
        usage: 42.7,
      ),
      gpus: [
        GpuInfo(
          model: 'Mali-G78 MP14',
          vendor: 'ARM',
          memoryUsed: 512,
          memoryTotal: 2048,
        ),
      ],
      disks: [
        DiskInfo(
          mount: '/',
          fs: 'ext4',
          used: 64.0,
          total: 128.0,
          usage: 50.0,
        ),
      ],
      os: OsInfo(
        platform: 'Android',
        distro: 'Android 15',
        release: '15',
        kernel: '6.1.0',
        arch: 'aarch64',
        hostname: 'StarTool-Device',
      ),
    );
  }
}
