package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.entity.AreaDic;
import cn.ekgc.itrip.base.transport.AreaDicTransport;
import cn.ekgc.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("hotelTransport")
@RequestMapping("/area/trans")
public class AreaDicTransportimpl implements AreaDicTransport {
	@Autowired
	private AreaDicService areaDicService;
	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 */
	@PostMapping(value = "/query")
	public List<AreaDic> getHotCityList(AreaDic query) throws Exception {
		return areaDicService.getHotCityList(query);
	}
}
