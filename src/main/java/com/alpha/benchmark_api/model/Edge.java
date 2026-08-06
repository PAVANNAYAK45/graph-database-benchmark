package com.alpha.benchmark_api.model;

public class Edge {

    private long source;

    private long destination;

    public Edge() {
    }

    public Edge(long source, long destination) {
        this.source = source;
        this.destination = destination;
    }

    public long getSource() {
        return source;
    }

    public void setSource(long source) {
        this.source = source;
    }

    public long getDestination() {
        return destination;
    }

    public void setDestination(long destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return source + " -> " + destination;
    }
}