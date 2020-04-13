package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.vo.HotelRoom;
import cn.ekgc.itrip.base.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.base.pojo.vo.validateRoomStoreVO;
import cn.ekgc.itrip.base.transport.HotelRoomTransport;
import cn.ekgc.itrip.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("hotelRoomTransport")
@RequestMapping("/hotelroom/trans")
public class HotelRoomTransportimpl implements HotelRoomTransport {
    @Autowired
	private HotelRoomService hotelRoomService;
	/**
	 * <b>根据roomid查询hotelroom对象</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryhotelroombyhotel")
	public List<HotelRoom> getHotelRoomByHotel(SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		return hotelRoomService.getHotelRoomByQuery(searchHotelRoomVO);
	}

	/**
	 * <b>根据房间Id的查询对象</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	public HotelRoom queryHotelRoomByhotel(Long roomId) throws Exception {
		return hotelRoomService.getHotelRoomById(roomId);
	}

	/**
	 * <b>根据入住时间和退房时间，查询该房间所剩数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	public int queryHotelRoomStoreByDate(validateRoomStoreVO validateRoomStoreVO) throws Exception {
		return hotelRoomService.getHotelByDate(validateRoomStoreVO);
	}
}
