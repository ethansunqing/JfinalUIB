package little.ant.pingtai.thread;

import java.util.Timer;
import java.util.TimerTask;

import little.ant.pingtai.tools.ToolOS;

import org.apache.log4j.Logger;

public class TimerSystemInfo extends Timer {

	private static Logger log = Logger.getLogger(TimerSystemInfo.class);
	
	/**
	 * 定时任务对象
	 */
	private static TimerSystemInfo timer = null;
	
	/**
	 * 启动任务
	 */
	public static void start() {
		if(null != timer){
			log.info("启动失败，任务已经启动");
			return;
		}
		log.info("开始启动任务");
		timer = new TimerSystemInfo();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				log.info("任务执行开始");
				String osName = ToolOS.getOsName(); // 获取操作系统类型名称
				String ip = ToolOS.getOsLocalHostIp(); // 获取本机IP
				String hostName = ToolOS.getOsLocalHostName(); // 获取本机名称
				int cpuCount = ToolOS.getOsCpuNumber(); // 获取CPU数量
				double cpuRatio = ToolOS.getOscpuRatio(); // cpu使用率
				
				long phyMemory = ToolOS.getOsPhysicalMemory(); // 物理内存，总的可使用的
				long phyFreeMemory = ToolOS.getOsPhysicalFreeMemory(); // 物理内存，剩余
				
				long jvmTotalMemory = ToolOS.getJvmTotalMemory(); // JVM内存，内存总量
				long jvmFreeMemory = ToolOS.getJvmFreeMemory(); // JVM内存，空闲内存量
				long jvmMaxMemory = ToolOS.getJvmMaxMemory(); // JVM内存，最大内存量
				long gcCount = ToolOS.getJvmGcCount(); // 获取JVM GC次数
				
				System.out.println("osName：" + osName);
				System.out.println("ip：" + ip);
				System.out.println("hostName：" + hostName);
				System.out.println("cpuCount：" + cpuCount);
				System.out.println("cpuRatio：" + cpuRatio);
				System.out.println("phyMemory：" + phyMemory);
				System.out.println("phyFreeMemory：" + phyFreeMemory);
				System.out.println("jvmTotalMemory：" + jvmTotalMemory);
				System.out.println("jvmFreeMemory：" + jvmFreeMemory);
				System.out.println("jvmMaxMemory：" + jvmMaxMemory);
				System.out.println("gcCount：" + gcCount);
				
				log.info("任务执行结束");
			}
		}, 1000, 1000 * 60 * 2);// 启动项目一秒后执行，然后每次间隔2分钟
		log.info("启动任务完成");
	}
	
	/**
	 * 停止任务
	 */
	public static void stop(){
		if(null != timer){
			log.info("任务退出开始");
			timer.cancel();
			log.info("任务退出成功");
		}else{
			log.info("任务退出失败，任务为空");
		}
	}
	
}
