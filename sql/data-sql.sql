//常用sql

//移动节点

001727上级改为001730

001684上级改为001730

001730   lft = 25  rgt = 26  user_id = 13

001727   lft = 23  rgt = 24  user_id = 8

001684   lft = 23  rgt = 24  user_id = 9

truncate tree_map;

insert into tree_map select user_id, lft, rgt, p_id from user where user_type <> 1;



DROP VIEW vw_lftrgt;
CREATE VIEW `vw_lftrgt` AS select `tree_map`.`lft` AS `lft` from `tree_map` union select `tree_map`.`rgt` AS `rgt` from `tree_map`;


call r_tree_traversal('move', 9, 8);


update user, tree_map as a set user.lft = a.lft, user.rgt = a.rgt, user.p_id = a.parent_id where user.user_id = a.node_id;

update user set p_game_id = '001727' where game_id = '001684';


1.玉林玩法七小对自摸每家8分改为每家4分。
2.南宁和玉林玩法都增加8个码，10个码，12个码的选项。
3.玉林玩法的直杠和暗杠不能抢杠，只有先碰再摸上来的明杠才能抢杠。
4.我们现在先做活动，需要把开房消耗的钻石改成4局10钻石，8局15钻石，12局20钻石，16局25钻石。
5.新号赠送的钻石从150改为100。


6.修改出牌动画，参考友乐出牌的动画
7.修改游戏音效，参考QQ麻将的音效




