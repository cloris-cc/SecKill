package org.cloris.controller;

import org.cloris.rabbitmq.MQSender;
import org.cloris.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class MQTestController {

	@Autowired
	MQSender sender;

	@GetMapping("/direct")
	public Result<String> mq() {
		sender.send("a test message");
		return Result.success("处理成功");
	}

	@GetMapping("/topic")
	public Result<String> topic() {
		sender.sendTopic("a test message");
		return Result.success("处理成功");
	}

	@GetMapping("/fanout")
	public Result<String> fanout() {
		sender.sendFanout("a test message");
		return Result.success("处理成功");
	}
	
	@GetMapping("/headers")
	public Result<String> headers() {
		sender.sendHeader("a test message");
		return Result.success("处理成功");
	}
	

}
