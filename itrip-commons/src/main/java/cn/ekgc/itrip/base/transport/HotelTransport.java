package cn.ekgc.itrip.base.transport;

import cn.ekgc.itrip.base.pojo.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name ="itrip-biz-provider")
@RequestMapping("/hotel/trans")
public interface HotelTransport {
	/**
	 * <b>根据酒店id查询信息</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	Hotel getListByQuery(Long hotelId)throws Exception;
}
