package com.lanhun;

import com.lanhun.example.service.HelloService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private HelloService helloService;
 
    public static void main(String[] args)  {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception {
        logger.info("System Started");
       String ret= helloService.hello("Boot");
        logger.info("Boot Service Result: "+ret);
        while(true){
            BufferedReader br = new BufferedReader(new InputStreamReader( System.in ) ) ;
            String command = br.readLine( );
            if("exit".equals(command)){
                logger.info("接收到正确的退出命令，系统退出");
                break;
            }
            logger.info("接收到未识别命令:"+command);
        }
        //System.out.println("系统启动完成");
        //do something
    }
}
