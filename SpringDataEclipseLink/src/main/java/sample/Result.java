package sample;


 record Result(int actorCount,long ms) {
    @Override
    public String toString() {
        return actorCount + " in " + ms + "ms";
    }
}
