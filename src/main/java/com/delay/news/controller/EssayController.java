package com.delay.news.controller;

import com.delay.news.entity.Essay;
import com.delay.news.entity.Fav;
import com.delay.news.entity.Type;
import com.delay.news.entity.User;
import com.delay.news.service.EssayService;
import com.delay.news.service.FavService;
import com.delay.news.service.TypeService;
import com.delay.news.utils.ExecuteResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("essay")
public class EssayController extends BaseController {

    @Autowired
    private EssayService essayService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private FavService favService;


    //保存文章
    @RequestMapping("save")
    @ResponseBody
    public ExecuteResult save(@RequestBody Essay essay){
        User user = getUser();
        Integer typeId = essay.getType().getId();
        Type type = typeService.findById(typeId);
        if(essay.getId()==null){
            essay.setUser(user);
            essay.setCreateTime(new Date());
            essay.setType(type);
            essay.setState(0);
            essayService.saveOrUpdate(essay);
        }else{
            Essay byId = essayService.findById(essay.getId());
            BeanUtils.copyProperties(essay,byId,"createTime","user","publishTime","type");
//            byId.setType("稿件");
            byId.setState(0);
            byId.setUpdateTime(new Date());

            essayService.saveOrUpdate(byId);
        }

        return ExecuteResult.ok();
    }

    //通过类型找文章
    @RequestMapping("findByType")
    @ResponseBody
    public ExecuteResult findByType(Integer typeId){
        List<Essay> essays = essayService.findAllByTypeId(typeId);
        return ExecuteResult.ok(essays);
    }

    //获取全部新闻
    @RequestMapping("getAllEssay")
    @ResponseBody
    public ExecuteResult getAllEssay(){
        User user = getUser();
        List<Essay> essays = essayService.findAllById(user.getId());
        return ExecuteResult.ok(essays);
    }

    //根据新闻状态查找
    @RequestMapping("getEssayByState")
    @ResponseBody
    public ExecuteResult getEssayByState(@RequestParam("state") Integer state){
        List<Essay> essays = essayService.findByState(state);
        return ExecuteResult.ok(essays);
    }

    //设置新闻状态
    @RequestMapping("setState")
    @ResponseBody
    public ExecuteResult setState(@RequestParam("state") Integer state,@RequestParam("essayId") Integer essayId){
        Essay byId = essayService.findById(essayId);
        byId.setState(state);
        if(state.equals(1)){
            byId.setPublishTime(new Date());
        }
        essayService.saveOrUpdate(byId);
        return ExecuteResult.ok();
    }

    //查看新闻
    @RequestMapping("checkEssay")
    public String checkEssay(@RequestParam("essayId") Integer essayId, Model model){

        Essay essay = essayService.findById(essayId);
        model.addAttribute("essay",essay);
        return "author/essay-show";
    }

    //前往修改新闻页面
    @RequestMapping("toEditEssay")
    public String toEditEssay(@RequestParam("essayId") Integer essayId, Model model){
        Essay essay = essayService.findById(essayId);
        model.addAttribute("essay",essay);
        return "author/editor";
    }



    //查看收藏
    @RequestMapping("findByFav")
    @ResponseBody
    public ExecuteResult<List<Essay>> findByFav(Model model){
        User user2 = (User)getSession().getAttribute("user2");
        List<Fav> favs = favService.findByUserId(user2.getId());
        List<Essay> list = new ArrayList<>();
        favs.stream().forEach(o->{
            Essay essay = essayService.findById(o.getNewsId());
            list.add(essay);
        });
        return ExecuteResult.ok(list);
    }

    //删除新闻
    @RequestMapping("delete")
    @ResponseBody
    public ExecuteResult delete(@RequestBody List<Integer> ids){
        if(CollectionUtils.isEmpty(ids)){
            return ExecuteResult.fail(1,"未选择一列");
        }
        ids.stream().forEach(o->{
            essayService.delete(o);
        });
        return ExecuteResult.ok();
    }

    //打回
    @RequestMapping("refuse")
    @ResponseBody
    public ExecuteResult refuse(@RequestParam("essayId")Integer essayId,@RequestParam("remark")String remark){
        Essay byId = essayService.findById(essayId);
        byId.setRemark(remark);
        byId.setState(2);
        essayService.saveOrUpdate(byId);
        return ExecuteResult.ok();
    }

    //弃用
    @RequestMapping("disuse")
    @ResponseBody
    public ExecuteResult disuse(@RequestParam("essayId")Integer essayId,@RequestParam("remark")String remark){
        Essay byId = essayService.findById(essayId);
        byId.setState(3);
        byId.setRemark(remark);
        essayService.saveOrUpdate(byId);
        return ExecuteResult.ok();
    }

    //添加收藏
    @RequestMapping("addFav")
    @ResponseBody
    public ExecuteResult addFav(Integer essayId){
        User user2 = (User)getSession().getAttribute("user2");
        Fav fav22 = favService.findByUserIdAndEssayId(user2.getId(),essayId);
        if(fav22!=null){
            return ExecuteResult.fail(1,"该新闻已收藏");
        }
        Fav fav = new Fav();
        fav.setCreateTime(new Date());
        fav.setNewsId(essayId);
        fav.setUserId(user2.getId());
        favService.saveOrUpdate(fav);
        return ExecuteResult.ok();
    }


}
