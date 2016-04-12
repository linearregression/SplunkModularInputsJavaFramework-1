package com.splunk.modinput.kinesis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.splunk.modinput.kinesis.KinesisModularInput.MessageReceiver;

public class JSONOnlyMessageHandler extends AbstractMessageHandler {

	@Override
	public void handleMessage(String record, byte [] rawBytes,String seqNumber,
			String partitionKey, MessageReceiver context) throws Exception {

		String text = stripNewlines(record);

		transportMessage(text, String.valueOf(System.currentTimeMillis()), "");

	}

	public static List<String> chunkData(String text, int size) {

		List<String> ret = new ArrayList<String>((text.length() + size - 1)
				/ size);

		for (int start = 0; start < text.length(); start += size) {
			ret.add(text.substring(start, Math.min(text.length(), start + size)));
		}
		return ret;
	}

	@Override
	public void setParams(Map<String, String> params) {
		// Do nothing , params not used

	}

}
