package cn.ekgc.itrip.base.transport;

import cn.ekgc.itrip.base.pojo.entity.HotelOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotelorder/trans")
public interface HotelOrderTransport {
	/**
	 * <b>查询用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	List<HotelOrder> getHotelListByQuery(@RequestBody HotelOrder query)throws Exception;
	/**
	 * <b>保存用户信息</b>
	 * @param hotelOrder
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	boolean save(@RequestBody  HotelOrder hotelOrder)throws Exception;

	/**
	 * <b>更改用户信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	boolean update(@RequestBody HotelOrder hotelOrder)throws Exception;

	/**
	 * <b>使用订单Id查询用户信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
    HotelOrder getHotelById(@RequestParam Long orderId)throws Exception;

	/**
	 * <b>使用订单号查询用户信息</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
    @PostMapping(value = "/no")
	HotelOrder getHotelOrderNo(@RequestParam String orderNo)throws Exception;
}
