package cn.ekgc.itrip.base.transport;

import cn.ekgc.itrip.base.pojo.entity.AreaDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 */
@FeignClient(name ="itrip-biz-provider")
@RequestMapping("/area/trans")
public interface AreaDicTransport {
	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 */
    @PostMapping(value = "/query")
	List<AreaDic> getHotCityList(AreaDic query)throws Exception;
}
