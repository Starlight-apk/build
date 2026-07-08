class SystemInfo {
  final CpuInfo cpu;
  final RamInfo ram;
  final List<GpuInfo> gpus;
  final List<DiskInfo> disks;
  final OsInfo os;

  SystemInfo({
    required this.cpu,
    required this.ram,
    required this.gpus,
    required this.disks,
    required this.os,
  });
}

class CpuInfo {
  final String model;
  final double usage;
  final double currentFreq;
  final double maxFreq;
  final int cores;

  CpuInfo({
    required this.model,
    required this.usage,
    required this.currentFreq,
    required this.maxFreq,
    required this.cores,
  });
}

class RamInfo {
  final double used;
  final double total;
  final double usage;

  RamInfo({
    required this.used,
    required this.total,
    required this.usage,
  });
}

class GpuInfo {
  final String model;
  final String vendor;
  final double memoryUsed;
  final double memoryTotal;

  GpuInfo({
    required this.model,
    required this.vendor,
    required this.memoryUsed,
    required this.memoryTotal,
  });
}

class DiskInfo {
  final String mount;
  final String fs;
  final double used;
  final double total;
  final double usage;

  DiskInfo({
    required this.mount,
    required this.fs,
    required this.used,
    required this.total,
    required this.usage,
  });
}

class OsInfo {
  final String platform;
  final String distro;
  final String release;
  final String kernel;
  final String arch;
  final String hostname;

  OsInfo({
    required this.platform,
    required this.distro,
    required this.release,
    required this.kernel,
    required this.arch,
    required this.hostname,
  });
}
