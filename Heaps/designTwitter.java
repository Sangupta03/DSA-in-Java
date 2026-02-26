package Heaps;
import java.util.*;
class designTwitter {

    // Tweet class
    static class Tweet {
        int tweetId;
        int time;

        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    // Node class for heap
    static class Node {
        int userId;
        int index;
        Tweet tweet;

        Node(int userId, int index, Tweet tweet) {
            this.userId = userId;
            this.index = index;
            this.tweet = tweet;
        }
    }

    HashMap<Integer, HashSet<Integer>> follow = new HashMap<>();
    HashMap<Integer, LinkedList<Tweet>> tweets = new HashMap<>();
    int time = 0;

    public designTwitter() {
    }

    public void postTweet(int userId, int tweetId) {

        if (!tweets.containsKey(userId)) {
            tweets.put(userId, new LinkedList<>());
        }

        tweets.get(userId).addFirst(new Tweet(tweetId, time));
        time++;
    }

    public List<Integer> getNewsFeed(int userId) {

        PriorityQueue<Node> heap =
                new PriorityQueue<>((a, b) -> b.tweet.time - a.tweet.time);

        if (!follow.containsKey(userId)) {
            follow.put(userId, new HashSet<>());
        }

        follow.get(userId).add(userId);  // user sees own tweets

        for (int user : follow.get(userId)) {

            if (!tweets.containsKey(user)) continue;

            LinkedList<Tweet> list = tweets.get(user);

            if (!list.isEmpty()) {
                heap.add(new Node(user, 0, list.get(0)));
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!heap.isEmpty() && result.size() < 10) {

            Node top = heap.poll();
            result.add(top.tweet.tweetId);

            int nextIdx = top.index + 1;
            LinkedList<Tweet> list = tweets.get(top.userId);

            if (nextIdx < list.size()) {
                heap.add(new Node(
                        top.userId,
                        nextIdx,
                        list.get(nextIdx)
                ));
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {

        if (!follow.containsKey(followerId)) {
            follow.put(followerId, new HashSet<>());
        }

        follow.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (!follow.containsKey(followerId)) return;

        follow.get(followerId).remove(followeeId);
    }
}