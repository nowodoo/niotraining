package com.zach.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerMain {
public static final String SHUTDOWN_HOOK_KEY = "server.shutdown.hook";
	    
	    private static volatile boolean running = true;
	public static void main(String[] args) {
				 long t = System.currentTimeMillis();
			        try
			        {
//			            logger.warn(Constants.SERVER_NAME + " init...");
			            
			            final ClassPathXmlApplicationContext context =
			                new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
			            if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY)))
			            {
			                Runtime.getRuntime().addShutdownHook(new Thread()
			                {
			                    public void run()
			                    {
			                        try
			                        {
			                            context.stop();
//			                            logger.warn(Constants.SERVER_NAME + " stopped!");
			                        }
			                        catch (Throwable t)
			                        {
			                        }
			                        synchronized (ServerMain.class)
			                        {
			                            running = false;
			                            ServerMain.class.notify();
			                        }
			                    }
			                });
			            }
			            
			            System.out.println("服务器已启动");
			            context.start();
			           
//			            logger.warn(Constants.SERVER_NAME + " started! takes " + (System.currentTimeMillis() - t) + " ms");
			        }
			        catch (RuntimeException e)
			        {
			            e.printStackTrace();
			            System.exit(1);
			        }
			        synchronized (ServerMain.class)
			        {
			            while (running)
			            {
			                try
			                {
			                	ServerMain.class.wait();
			                }
			                catch (Throwable e)
			                {
			                }
			            }
			        }
			}

}
