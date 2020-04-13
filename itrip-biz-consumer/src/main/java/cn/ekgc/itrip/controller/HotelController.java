package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.controller.Enum.AreaHotEnum;
import cn.ekgc.itrip.base.controller.Enum.ImageTypeEnum;
import cn.ekgc.itrip.base.controller.pojo.vo.ResponseDto;
import cn.ekgc.itrip.base.pojo.entity.AreaDic;
import cn.ekgc.itrip.base.pojo.entity.Hotel;
import cn.ekgc.itrip.base.pojo.entity.ItripImage;
import cn.ekgc.itrip.base.pojo.entity.LableDic;
import cn.ekgc.itrip.base.pojo.vo.SearchDetailsHotelVO;
import cn.ekgc.itrip.base.transport.AreaDicTransport;
import cn.ekgc.itrip.base.transport.HotelTransport;
import cn.ekgc.itrip.base.transport.ItripImageTransport;
import cn.ekgc.itrip.base.transport.LableDicTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("hotelController")
@RequestMapping("/biz/api/hotel")
public class HotelController extends BaseController {
	@Autowired
	private AreaDicTransport areaDicTransport;
	@Autowired
	private LableDicTransport lableDicTransport;
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private ItripImageTransport itripImageTransport;
	/**
	 * <b>搜索热门城市</b>
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
    @GetMapping(value = "/queryhotcity/{isChina}")
	public ResponseDto<Object> queryHotelCityList(@PathVariable("isChina") Integer isChina )throws Exception{
	    AreaDic query  = new AreaDic();
	    query.setIsChina(isChina);
	    query.setIsHot(AreaHotEnum.AREA_HOT_YES.getCode());
	    List<AreaDic> areaDicList = areaDicTransport.getHotCityList(query);
	    	return ResponseDto.success(areaDicList);
	}

	/**
	 * <b>查询酒店及酒店特色</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelfeature")
	public ResponseDto<Object> queryHotelFeaTure()throws Exception{
		LableDic query = new LableDic();
		query.setParentId(16L);
		List<LableDic> lableDicList =lableDicTransport.getqueryHotelFeaTure(query);
		return ResponseDto.success(lableDicList);
	}

	/**
	 * <b>根据酒店id查询酒店特色、商圈、酒店名称（视频）</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getvideodesc/{hotelId}")
	 public ResponseDto<Object> getVideoDesc(@PathVariable("hotelId") Long hotelId)throws Exception{
		Hotel hotel = hotelTransport.getListByQuery(hotelId);
		return ResponseDto.success(hotel);
	 }

	/**
	 * <b>根据酒店id查特色和介绍</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhoteldetails/{hotelId}")
	public ResponseDto<Object> queryHotelDetails(@PathVariable("hotelId") Long hotelId) throws Exception {
      List<SearchDetailsHotelVO> hotelVOList = new ArrayList<SearchDetailsHotelVO>();
      Hotel hotel = hotelTransport.getListByQuery(hotelId);
      hotelVOList.add(new SearchDetailsHotelVO("酒店介绍",hotel.getDetails()));
      LableDic query = new LableDic();
      query.setId(hotelId);
      List<LableDic> lableDicList = lableDicTransport.getqueryHotelFeaTure(query);
	  if (lableDicList != null && lableDicList.size() > 0 ){
		  for (LableDic lableDic : lableDicList){
			  hotelVOList.add(new SearchDetailsHotelVO(lableDic.getName(),lableDic.getDescription()));
		  }
	  }
	  return ResponseDto.success(hotelVOList);
	}

	/**
	 *<b>根据酒店id查询酒店设施</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
    @GetMapping(value = "/queryhotelfacilities/{hotelId}")
	public ResponseDto<Object> queryHotelFacilities(@PathVariable("hotelId") Long hotelId)throws Exception{
      Hotel hotel= hotelTransport.getListByQuery(hotelId);
      return ResponseDto.success(hotel.getFacilities());
	}

	/**
	 * <b>根据酒店id查询酒店政策</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
   @GetMapping(value = "/queryhotelpolicy/{hotelId}")
	public ResponseDto<Object> queryHotelPolicy(@PathVariable("hotelId") Long hotelId)throws Exception{
       Hotel hotel = hotelTransport.getListByQuery(hotelId);
       return ResponseDto.success(hotel.getHotelPolicy());
	}

	/**
	 * <b>根据targetId查询酒店图片（type=0）</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getimg/{targetId}")
	public ResponseDto<Object> getImgForHotel(@PathVariable("targetId") Long targetId)throws Exception{
		ItripImage query = new ItripImage();
		query.setTargetId(targetId);
        query.setType(String.valueOf(ImageTypeEnum.IMAGE_TYPE_HOTEL.getCode()));
        List<ItripImage> imageList = itripImageTransport.getImageListByQuery(query);
       if (imageList.size() > 0 ){
       	return ResponseDto.success(imageList.get(0));
       }
       return ResponseDto.success(new ItripImage());
	}
}
