package com.infotrends.in.SpringbootV1.data;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence_generator")
public class SeqGenerator {

	@Id
	private int seqNo;
	
	private String type;

	
	
	
	public SeqGenerator() {
		super();
	}

	
	
	public SeqGenerator(int seqNo, String type) {
		super();
		this.seqNo = seqNo;
		this.type = type;
	}



	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int id) {
		this.seqNo = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
