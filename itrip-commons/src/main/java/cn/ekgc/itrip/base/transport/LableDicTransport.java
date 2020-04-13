package cn.ekgc.itrip.base.transport;

import cn.ekgc.itrip.base.pojo.entity.LableDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name ="itrip-biz-provider")
@RequestMapping("/label/trans")
public interface LableDicTransport {
	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/query")
	List<LableDic> getqueryHotelFeaTure(LableDic query)throws Exception;
}
