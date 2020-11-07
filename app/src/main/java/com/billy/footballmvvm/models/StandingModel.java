package com.billy.footballmvvm.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingModel {
    @SerializedName("table")
    private List<Table> table = null;

    public List<Table> getTable() {
        return table;
    }

    public static class Table{
        @SerializedName("name")
        private String name;
        @SerializedName("teamid")
        private String teamid;
        @SerializedName("played")
        private Integer played;
        @SerializedName("goalsfor")
        private Integer goalsfor;
        @SerializedName("goalsagainst")
        private Integer goalsagainst;
        @SerializedName("goalsdifference")
        private Integer goalsdifference;
        @SerializedName("win")
        private Integer win;
        @SerializedName("draw")
        private Integer draw;
        @SerializedName("loss")
        private Integer loss;
        @SerializedName("total")
        private Integer total;

        public String getName() {
            return name;
        }

        public String getTeamid() {
            return teamid;
        }

        public Integer getPlayed() {
            return played;
        }

        public Integer getGoalsfor() {
            return goalsfor;
        }

        public Integer getGoalsagainst() {
            return goalsagainst;
        }

        public Integer getGoalsdifference() {
            return goalsdifference;
        }

        public Integer getWin() {
            return win;
        }

        public Integer getDraw() {
            return draw;
        }

        public Integer getLoss() {
            return loss;
        }

        public Integer getTotal() {
            return total;
        }
    }
}
