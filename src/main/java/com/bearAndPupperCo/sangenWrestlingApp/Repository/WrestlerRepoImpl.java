package com.bearAndPupperCo.sangenWrestlingApp.Repository;

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
    public List<WrestlerMainTableDTO> findWrestlerListByParams(Integer pageNumber, Integer pageSize, Integer brandId, Integer lockerRoomId,
                                                               String orderBy, String orderDirection) {
        int offset = (pageNumber - 1) * pageSize;

        String sql = "SELECT " +
                "w.in_ring_name, " +
                "w.wrestling_streak, " +
                "wb.wrestling_brand_id, " +
                "wb.wrestling_brand_name, " +
                "lr.wrestling_locker_room_id," +
                "lr.wrestling_locker_room_name, " +
                "COALESCE(COUNT(DISTINCT mv.winner_wrestler_id), 0) AS total_victories, " +
                "COALESCE(COUNT(DISTINCT ml.loser_wrestler_id), 0) AS total_losses, " +
                "IFNULL(" +
                "        ROUND( " +
                "            (COALESCE(COUNT(DISTINCT mv.winner_wrestler_id), 0) * 100.0) / " +
                "            NULLIF((COALESCE(COUNT(DISTINCT mv.winner_wrestler_id), 0) + COALESCE(COUNT(DISTINCT ml.loser_wrestler_id), 0)), 0), " +
                "            2), " +
                "    0) AS percentage_of_victories " +
                "FROM " +
                "wrestler w " +
                "JOIN " +
                "wrestling_brand wb ON w.wrestling_brand_id = wb.wrestling_brand_id " +
                "JOIN " +
                "locker_room lr ON w.wrestling_locker_room_id = lr.wrestling_locker_room_id " +
                "LEFT JOIN " +
                "wrestler_match_victories mv ON w.wrestler_id = mv.winner_wrestler_id " +
                "LEFT JOIN " +
                "wrestler_match_losses ml ON w.wrestler_id = ml.loser_wrestler_id " +
                "WHERE " +
                "(:brandId IS NULL OR w.wrestling_brand_Id = :brandId) " +
                "AND (:lockerRoomId IS NULL OR w.wrestling_locker_room_id = :lockerRoomId) " +
                "GROUP BY " +
                "w.in_ring_name, " +
                "w.wrestling_streak, " +
                "wb.wrestling_brand_id, " +
                "wb.wrestling_brand_name, " +
                "lr.wrestling_locker_room_id, " +
                "lr.wrestling_locker_room_name " +
                "ORDER BY " + orderBy + " " + orderDirection + " " +
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
