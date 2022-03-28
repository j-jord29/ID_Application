package task1;

public class ID implements Comparable<ID> {

    private int id = 000000;

    public ID() {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setID(int i) {
        this.id = i;
    }

    public int compareTo(ID o) {
        if (id == o.id) {
            return 0;
        } else if (id > o.id) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        return ("ID = "+id);
    }

    public boolean checkIDLength(String id) throws Exception {
        int idLength = 0;
        String[] splitString = id.split("");
        for (String i : splitString) {
            idLength += 1;
        }
        if (idLength != 6) {
            return false;
        } else {
            return true;
        }
    }
}

