package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.entity.Hotel;
import cn.ekgc.itrip.base.transport.HotelTransport;
import cn.ekgc.itrip.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("hotelTransport")
@RequestMapping("/hotel/trans")
public class HotelTransportimpl implements HotelTransport {
	@Autowired
	private HotelService hotelService;
	/**
	 * <b>根据酒店id查询信息</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	public Hotel getListByQuery(Long hotelId) throws Exception {
		return hotelService.getListByQuery(hotelId);
	}
}
