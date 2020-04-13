package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.entity.LableDic;
import cn.ekgc.itrip.base.transport.LableDicTransport;
import cn.ekgc.itrip.service.LableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController("lableTransport")
@RequestMapping("/label/trans")
public class LableTransportimpl implements LableDicTransport {
	@Autowired
	private LableService lableService;
	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	public List<LableDic> getqueryHotelFeaTure(LableDic query) throws Exception {
		return lableService.getListByQuery(query);
	}
}
