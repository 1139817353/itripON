package cn.ekgc.itrip.base.transport;

import cn.ekgc.itrip.base.pojo.vo.HotelRoom;
import cn.ekgc.itrip.base.pojo.vo.SearchDetailsHotelVO;
import cn.ekgc.itrip.base.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.base.pojo.vo.validateRoomStoreVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotelroom/trans")
public interface HotelRoomTransport {
	/**
	 * <b>根据roomid查询hotelroom对象</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryhotelroombyhotel")
	List<HotelRoom> getHotelRoomByHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO)throws Exception;

	@PostMapping(value = "/id")
	HotelRoom queryHotelRoomByhotel(Long roomId)throws Exception;

	@PostMapping(value = "/store")
	int queryHotelRoomStoreByDate(validateRoomStoreVO validateRoomStoreVO)throws Exception;
}
