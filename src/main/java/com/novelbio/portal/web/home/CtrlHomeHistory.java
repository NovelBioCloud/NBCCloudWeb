package com.novelbio.portal.web.home;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtHistory;
import com.novelbio.portal.biz.mgmt.MgmtNews;
import com.novelbio.portal.biz.mgmt.MgmtRecruitment;
import com.novelbio.portal.biz.model.History;

@Controller
@RequestMapping(value = "/home/history")
public class CtrlHomeHistory extends AbstractController {
	@Autowired
	MgmtNews mgmtNews;
	@Autowired
	MgmtHistory mgmtHistory;
	@Autowired
	MgmtRecruitment mgmtRecruitment;

	@ResponseBody
	@RequestMapping("getList")
	ResultJson getListHistory() {
		try {
			List<History> historyList = mgmtHistory.list();
			Collections.sort(historyList, (o2, o1) -> {
				if (o1.getTime() == null && o2.getTime() == null) {
					return 0;
				} else if (o1.getTime() != null && o2.getTime() == null) {
					return 1;
				} else if (o1.getTime() == null && o2.getTime() != null) {
					return -1;
				} else {
					long time1 = o1.getTime().getTime();
					long time2 = o2.getTime().getTime();
					if (time1 > time2) {
						return 1;
					} else if (time1 == time2) {
						return 0;
					} else {
						return -1;
					}
				}
			});
			List<History> result = historyList.stream().limit(10).collect(Collectors.toList());
			return ResultJson.trueState("", result);
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

}
