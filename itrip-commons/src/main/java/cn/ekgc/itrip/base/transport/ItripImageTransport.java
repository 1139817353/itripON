package cn.ekgc.itrip.base.transport;

import cn.ekgc.itrip.base.pojo.entity.ItripImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/img/trans")
public interface ItripImageTransport {
	/**
	 * <b>根据targetId查询酒店图片（type=0）</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/list")
	List<ItripImage> getImageListByQuery(ItripImage query)throws Exception;
}
