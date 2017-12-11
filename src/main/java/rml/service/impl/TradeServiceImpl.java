package rml.service.impl;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rml.dao.TradeMapper;
import rml.model.Trade;
import rml.service.SkuService;
import rml.service.TradeService;
import rml.service.VenderService;
import rml.util.DateUtil;
import rml.vo.Factory.TradeFactory;
import rml.vo.SkuVO;
import rml.vo.TradeVO;
import rml.vo.VenderVO;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by linzhongxia on 2017/10/17.
 */
@Service("tradeService")
public class TradeServiceImpl implements TradeService {
    @Resource
    private TradeMapper tradeMapper;
    @Resource
    private SkuService skuService;
    @Resource
    private VenderService venderService;

    @Override
    public long getOrderNum(Date date) {
        date = DateUtil.longToDate(date.getTime(), DateUtil.TIME_FROMAT_DAY);
        Long num = tradeMapper.getOrderNum(date);
        if (num == null) {
            return 0;
        }
        return num;
    }

    @Override
    public long getVenderNum(Date date) {
        date = DateUtil.longToDate(date.getTime(), DateUtil.TIME_FROMAT_DAY);
        Long num = tradeMapper.getVenderNum(date);
        if (num == null) {
            return 0;
        }
        return num;
    }

    @Override
    public long getSkuCount(TradeVO tradeVO) {
        Long count = tradeMapper.getSkuCount(tradeVO);
        if (count == null) {
            return 0;
        }
        return count;
    }

    @Override
    public List<TradeVO> getSkuList(TradeVO param) {
        List<Trade> tradeList = tradeMapper.getSkuList(param);

        if (CollectionUtils.isEmpty(tradeList)) {
            return null;
        }
        List<TradeVO> tradeVOList = new ArrayList<TradeVO>();
        for (Trade trade : tradeList) {
            TradeVO tradeVO = TradeFactory.INSTANCE.convertToTradeVO(trade);
            SkuVO skuVO = skuService.getById(tradeVO.getSkuId());
            if (skuVO == null) {
                // TODO: 2017/10/18 记录日志OR抛异常
                return null;
            }
            tradeVO.setWareId(skuVO.getWareId());
            tradeVO.setWareName(skuVO.getWareName());
            tradeVO.setSupplierId(skuVO.getSupplierId());
            tradeVO.setSkuName(skuVO.getName());
            tradeVO.setColour(skuVO.getColour());
            tradeVO.setSize(skuVO.getSize());
            tradeVO.setImg(skuVO.getImg());
            tradeVOList.add(tradeVO);
        }
        return tradeVOList;
    }

    @Override
    public XSSFWorkbook inReportExcel(TradeVO param) {
        // 第一步，创建一个webbook，对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet("提货单");
        XSSFRow row = sheet.createRow((int) 0);
        sheet.setDefaultColumnWidth(20);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
        try {
            XSSFCell cell = row.createCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue("序号");
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue("款名");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue("商品编号");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue("供货商");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue("明细");
            cell.setCellStyle(style);

            Map<String, List<TradeVO>> reportDate = this.getInReportDate(param);
            Set<String> styleSet = reportDate.keySet();
            if (!CollectionUtils.isEmpty(styleSet)) {
                int i = 0;
                for (String styleName : styleSet) {
                    List<TradeVO> tradeVOList = reportDate.get(styleName);
                    TradeVO tradeVO = tradeVOList.get(0);
                    row = sheet.createRow(i + 1);
                    cell = row.createCell(0);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(String.valueOf(i + 1));
                    cell.setCellStyle(style);

                    cell = row.createCell(1);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(styleName);
                    cell.setCellStyle(style);

                    cell = row.createCell(2);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(String.valueOf(tradeVO.getWareId()));
                    cell.setCellStyle(style);

                    cell = row.createCell(3);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(String.valueOf(tradeVO.getSupplierId()));
                    cell.setCellStyle(style);

                    cell = row.createCell(4);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    StringBuffer detail = new StringBuffer();
                    for (TradeVO sizeVO : tradeVOList) {
                        detail.append(" [" + sizeVO.getSize() + "]-" + sizeVO.getNum());
                    }
                    cell.setCellValue(detail.toString().substring(1));
                    cell.setCellStyle(style);
                    i++;
                }
            }
        } catch (Exception e) {
//            log.error("BargainManagerServiceImpl.downloadReportExcel idList:{},error:{}", idList, e);
            throw new RuntimeException("下载文件失败", e);
        }
        return wb;
    }

    public Map<String, List<TradeVO>> getInReportDate(TradeVO param) {
        Map<String, List<TradeVO>> reportDate = new HashMap<String, List<TradeVO>>();
        long count = this.getSkuCount(param);
        if (count > 0) {
            int times = (int) count / 100 + 1;
            for (int i = 1; i <= times; i++) {
                param.setPage(i);
                param.setPageSize(100);
                List<TradeVO> voList = this.getSkuList(param);
                for (TradeVO vo : voList) {
                    String mapKey = vo.getWareName() + " 【" + vo.getColour() + "】";
                    List<TradeVO> mapList = reportDate.get(mapKey);
                    if (CollectionUtils.isEmpty(mapList)) {
                        mapList = new ArrayList<TradeVO>();
                    }
                    TradeVO tradeVO = new TradeVO();
                    tradeVO.setSize(vo.getSize());
                    tradeVO.setNum(vo.getNum());
                    tradeVO.setWareId(vo.getWareId());
                    tradeVO.setSupplierId(vo.getSupplierId());
                    tradeVO.setImg(vo.getImg());

                    mapList.add(tradeVO);
                    reportDate.put(mapKey, mapList);
                }
            }
        }
        return reportDate;
    }

    @Override
    public List<TradeVO> getVenderList(TradeVO param) {
        List<Trade> tradeList = tradeMapper.getVenderList(param);

        if (CollectionUtils.isEmpty(tradeList)) {
            return null;
        }
        List<TradeVO> tradeVOList = new ArrayList<TradeVO>();
        for (Trade trade : tradeList) {
            TradeVO tradeVO = TradeFactory.INSTANCE.convertToTradeVO(trade);
            VenderVO venderVO = venderService.getById(tradeVO.getVenderId());
            if (venderVO == null) {
                // TODO: 2017/10/18 记录日志OR抛异常
                return null;
            }
            tradeVO.setVenderName(venderVO.getName());
            tradeVOList.add(tradeVO);
        }
        return tradeVOList;
    }

    @Override
    public long getVenderSkuCount(TradeVO tradeVO) {
        Long count = tradeMapper.getVenderSkuCount(tradeVO);
        if (count == null) {
            return 0;
        }
        return count;
    }

    @Override
    public List<TradeVO> getVenderSkuList(TradeVO tradeVO) {
        List<Trade> tradeList = tradeMapper.getVenderSkuList(tradeVO);
        if (CollectionUtils.isEmpty(tradeList)) {
            return null;
        }
        List<TradeVO> voList = new ArrayList<TradeVO>();
        for (Trade trade : tradeList) {
            TradeVO vo = TradeFactory.INSTANCE.convertToTradeVO(trade);
            SkuVO skuVO = skuService.getById(vo.getSkuId());
            if (skuVO == null) {
                // TODO: 2017/10/18 记录日志OR抛异常
                return null;
            }
            vo.setWareId(skuVO.getWareId());
            vo.setWareName(skuVO.getWareName());
            vo.setSupplierId(skuVO.getSupplierId());
            vo.setSkuName(skuVO.getName());
            vo.setColour(skuVO.getColour());
            vo.setSize(skuVO.getSize());
            vo.setImg(skuVO.getImg());
            VenderVO venderVO = venderService.getById(vo.getVenderId());
            if (venderVO == null) {
                // TODO: 2017/10/18 记录日志OR抛异常
                return null;
            }
            vo.setVenderName(venderVO.getName());

            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Map<String, List<TradeVO>> getOutReportDate(TradeVO param) {
        Map<String, List<TradeVO>> reportDate = new LinkedHashMap<String, List<TradeVO>>();
        long count = this.getVenderSkuCount(param);
        if (count > 0) {
            int times = (int) count / 100 + 1;
            for (int i = 1; i <= times; i++) {
                param.setPage(i);
                param.setPageSize(100);
                List<TradeVO> voList = this.getVenderSkuList(param);
                for (TradeVO vo : voList) {
                    String mapKey = vo.getVenderId() + "_" + vo.getWareId() + "_" + vo.getColour();
                    List<TradeVO> mapList = reportDate.get(mapKey);
                    if (CollectionUtils.isEmpty(mapList)) {
                        mapList = new ArrayList<TradeVO>();
                    }
                    TradeVO tradeVO = new TradeVO();
                    tradeVO.setVenderId(vo.getVenderId());
                    tradeVO.setVenderName(vo.getVenderName());
                    tradeVO.setColour(vo.getColour());
                    tradeVO.setSize(vo.getSize());
                    tradeVO.setNum(vo.getNum());
                    tradeVO.setWareId(vo.getWareId());
                    tradeVO.setWareName(vo.getWareName());
                    tradeVO.setSupplierId(vo.getSupplierId());
                    tradeVO.setImg(vo.getImg());

                    mapList.add(tradeVO);
                    reportDate.put(mapKey, mapList);
                }
            }
        }
        return reportDate;
    }

    @Override
    public boolean add(TradeVO tradeVO) {

        Trade trade = TradeFactory.INSTANCE.convertFromTradeVO(tradeVO);
        tradeMapper.insert(trade);

        return true;
    }

    @Override
    public boolean deleteById(Long tradeId) {

        int count = tradeMapper.deleteById(tradeId);
        if (count > 0) {
            return true;
        }

        return false;
    }

    @Override
    public long getCount(TradeVO tradeVO) {
        Long count = tradeMapper.getCount(tradeVO);
        if (count == null) {
            return 0;
        }
        return count;
    }

    @Override
    public List<TradeVO> getList(TradeVO tradeVO) {
        List<Trade> tradeList = tradeMapper.getList(tradeVO);

        if (CollectionUtils.isEmpty(tradeList)) {
            return null;
        }
        List<TradeVO> voList = new ArrayList<TradeVO>();
        for (Trade trade : tradeList) {
            TradeVO vo = TradeFactory.INSTANCE.convertToTradeVO(trade);
            //补全商家信息
            VenderVO venderVO = venderService.getById(vo.getVenderId());
            if (venderVO == null) {
                // TODO: 2017/10/18 记录日志OR抛异常
                return null;
            }
            vo.setVenderName(venderVO.getName());
            //补全商品信息
            SkuVO skuVO = skuService.getById(vo.getSkuId());
            if (skuVO == null) {
                // TODO: 2017/10/18 记录日志OR抛异常
                return null;
            }
            vo.setSkuName(skuVO.getName());
            vo.setSupplierId(skuVO.getSupplierId());
            vo.setImg(skuVO.getImg());

            voList.add(vo);
        }
        return voList;
    }
}
