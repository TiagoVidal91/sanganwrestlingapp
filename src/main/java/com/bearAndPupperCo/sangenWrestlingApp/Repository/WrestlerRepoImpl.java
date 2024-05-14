package com.bearAndPupperCo.sangenWrestlingApp.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.SingleWrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Mappers.WrestlerMainTableDTOMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WrestlerRepoImpl implements WrestlerRepo{

    private final Jdbi jdbi;

    public WrestlerRepoImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public List<Wrestler> findWrestlerByInRingName(String ringName) {
        return null;
    }

    @Override
    public SingleWrestlerDTO addNewWrestler(SingleWrestlerDTO wrestlerDTO) {
        return null;
    }

    @Override
    public List<WrestlerMainTableDTO> findWrestlerListByParams(Integer pageNumber, Integer pageSize, Integer brandId, Integer lockerRoomId,
                                                               String orderBy, String orderDirection) {
        int offset = (pageNumber - 1) * pageSize;

        String sql = "WITH Victories AS (" +
                "    SELECT " +
                "        winner_wrestler_id, " +
                "        COUNT(*) AS victory_count" +
                "    FROM " +
                "        wrestler_match_victories" +
                "    GROUP BY " +
                "        winner_wrestler_id" +
                ")," +
                "Losses AS (" +
                "    SELECT " +
                "        loser_wrestler_id, " +
                "        COUNT(*) AS loss_count" +
                "    FROM " +
                "        wrestler_match_losses" +
                "    GROUP BY " +
                "        loser_wrestler_id" +
                ")" +
                "SELECT " +
                "    w.in_ring_name, " +
                "    w.wrestling_streak, " +
                "    wb.wrestling_brand_id, " +
                "    wb.wrestling_brand_name, " +
                "    lr.wrestling_locker_room_id," +
                "    lr.wrestling_locker_room_name, " +
                "    COALESCE(v.victory_count, 0) AS total_victories, " +
                "    COALESCE(l.loss_count, 0) AS total_losses, " +
                "    CASE " +
                "        WHEN v.victory_count > 0 THEN " +
                "            ROUND(" +
                "                (v.victory_count * 100.0) / " +
                "                (v.victory_count + " +
                "                (CASE WHEN l.loss_count IS NULL THEN 0 ELSE l.loss_count END)), " +
                "                2" +
                "            ) " +
                "        ELSE " +
                "            0 " +
                "    END AS percentage_of_victories " +
                "FROM " +
                "    wrestler w " +
                "JOIN " +
                "    wrestling_brand wb ON w.wrestling_brand_id = wb.wrestling_brand_id " +
                "JOIN " +
                "    locker_room lr ON w.wrestling_locker_room_id = lr.wrestling_locker_room_id " +
                "LEFT JOIN " +
                "    Victories v ON w.wrestler_id = v.winner_wrestler_id " +
                "LEFT JOIN " +
                "    Losses l ON w.wrestler_id = l.loser_wrestler_id " +
                "WHERE " +
                "    (:brandId IS NULL OR w.wrestling_brand_Id = :brandId) " +
                "    AND (:lockerRoomId IS NULL OR w.wrestling_locker_room_id = :lockerRoomId) " +
                "GROUP BY " +
                "    w.in_ring_name, " +
                "    w.wrestling_streak, " +
                "    wb.wrestling_brand_id, " +
                "    wb.wrestling_brand_name, " +
                "    lr.wrestling_locker_room_id, " +
                "    lr.wrestling_locker_room_name, " +
                "    v.victory_count, " +
                "    l.loss_count " +
                "ORDER BY " + orderBy + " " + orderDirection + ", in_ring_name ASC " +
                "LIMIT :pageSize OFFSET :offset";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("brandId", brandId)
                        .bind("lockerRoomId", lockerRoomId)
                        .bind("pageSize", pageSize)
                        .bind("offset", offset)
                        .map(new WrestlerMainTableDTOMapper())
                        .list()
        );
    }

    @Override
    public int getTotalWrestlerCount(Integer brandId, Integer lockerRoomId) {
        String countSql = "SELECT COUNT(*) FROM wrestler w " +
                "JOIN wrestling_brand wb ON w.wrestling_brand_id = wb.wrestling_brand_id " +
                "JOIN locker_room lr ON w.wrestling_locker_room_id = lr.wrestling_locker_room_id " +
                "WHERE (:brandId IS NULL OR w.wrestling_brand_Id = :brandId) " +
                "AND (:lockerRoomId IS NULL OR w.wrestling_locker_room_id = :lockerRoomId)";

        return jdbi.withHandle(handle ->
                handle.createQuery(countSql)
                        .bind("brandId", brandId)
                        .bind("lockerRoomId", lockerRoomId)
                        .mapTo(Integer.class)
                        .one()
        );
    }
}
