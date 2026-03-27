package edson.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author edson 16/01/2019
 */
@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean messageReceived;
    private Integer messageCount = 0; //init to zero

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(boolean messageReceived) {
        this.messageReceived = messageReceived;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }
}
