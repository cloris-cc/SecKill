package org.cloris.rabbitmq;

import java.io.Serializable;

import org.cloris.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecKillMessage implements Serializable {

	private static final long serialVersionUID = 8642618221788678389L;

	private User user;
	private Long goodsId;
}
