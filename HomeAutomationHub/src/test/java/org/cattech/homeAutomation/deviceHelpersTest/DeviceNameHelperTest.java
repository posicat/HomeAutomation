package org.cattech.homeAutomation.deviceHelpersTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.cattech.homeAutomation.deviceHelpers.DeviceNameHelper;
import org.json.JSONArray;
import org.junit.Test;

public class DeviceNameHelperTest {

	@Test
	public void testVariousSubMatcesForCommonDescriptorsMatch() {
		
		verifyMatch("['upstairs','bedroom','master','desk','light']","['light']");
		verifyMatch("['upstairs','bedroom','master','desk','light']","['light','desk']");
		verifyMatch("['upstairs','bedroom','master','desk','light']","['master','light']");
		verifyMatch("['upstairs','bedroom','master','desk','light']","['bedroom','light']");
		verifyNoMatch("['upstairs','bedroom','master','desk','light']","['attic','light']");
		verifyNoMatch("['upstairs','bedroom','master','desk','light']","['attic']");
		verifyMatch("['upstairs','bedroom','master','desk','light']","[]");

		verifyNoMatch("['macro','mark','night stand']","['attic','light']");
	}

	private void verifyMatch(String full, String sub) {
		boolean match =  DeviceNameHelper.commonDescriptorsMatch(new JSONArray(full),new JSONArray(sub));
		assertTrue(sub + "should be a subset of " + full,match);
		
	}
	private void verifyNoMatch(String full, String sub) {
		boolean match =  DeviceNameHelper.commonDescriptorsMatch(new JSONArray(full),new JSONArray(sub));
		assertFalse(sub + "should NOT be a subset of " + full,match);
	}

}