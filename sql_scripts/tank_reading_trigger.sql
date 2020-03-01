create function is_caution_level(reading_pk int) returns table(is_caution bool) as
$$
begin
    return QUERY select tr.current_height <= tm.tank_caution_level
                 from tank_readings as tr
                          join tank_model tm on tr.tank_model_fk = tm.pk
                 where tr.pk = reading_pk;
end;
$$
language plpgsql;


create function add_to_worklist2() returns trigger as
$$
begin
    if is_caution_level(new.pk) then
        insert into manager_worklist (time_estimate_fk, resident_fk, tank_type)
        SELECT 6, u.pk, tr.tank_type_fk
        from tank_readings tr
                 join "user" u on tr.tank_owner_fk = u.pk
        where tr.pk = new.pk;
    end if;
    return new;
end;
$$
    language plpgsql;

create trigger add_to_worklist
    after insert
    on tank_readings
    for each row
execute procedure public.add_to_worklist2();

insert into tank_readings (current_height, tank_owner_fk, tank_type_fk, tank_model_fk)
values (180, 1, 1, 1);

insert into tank_readings (current_height, tank_owner_fk, tank_type_fk, tank_model_fk)
values (50, 1, 1, 1);

drop trigger add_to_worklist on tank_readings;
drop function add_to_worklist2();
